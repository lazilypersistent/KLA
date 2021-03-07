package com.klalibrary.daointerfaceimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.klalibrary.bean.Attachment;
import com.klalibrary.bean.Itinerary;
import com.klalibrary.bean.Request;
import com.klalibrary.daointerface.DashboardDao;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	
	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public List<Request> userRequests(int userId) {
		String requestSql = "select * from public.requests r where user_id=:userId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);

		List<Request> userRequestList = template.query(requestSql, param, new RowMapper<Request>() {
			@Override
			public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
				Request userRequest = new Request();
				userRequest.setAppliedDate(rs.getString("applied_date"));
				userRequest.setRequestId(rs.getInt("request_id"));
				userRequest.setRequestStatus(rs.getString("request_status"));
				userRequest.setTypeOfRequest(rs.getString("type_of_request"));
				
				String sql = "select * from public.itinerary i where i.request_id=:requestId;";
				SqlParameterSource param = new MapSqlParameterSource().addValue("requestId", userRequest.getRequestId());

				List<Itinerary> itineraryList = template.query(sql, param, new RowMapper<Itinerary>() {
					@Override
					public Itinerary mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Itinerary itinerary = new Itinerary();
						itinerary.setAuthor(rs.getString("author"));
						itinerary.setGenre(rs.getString("genre"));
						itinerary.setName(rs.getString("name"));
						itinerary.setPublication(rs.getString("publication"));
						itinerary.setItinerayType(rs.getString("itinerary_type"));
						return itinerary;
					}

				});
				userRequest.setItineraryList(itineraryList);
				return userRequest;
			}
		});
		return userRequestList;
	}

	@Override
	public int saveItinerary(Request request) {
		String sql = "INSERT INTO public.requests (applied_date, request_status, type_of_request, user_id)\n"
				+ "VALUES (:appliedDate, :requestStatus, :typeOfRequest, :userId); ";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("userId", request.getUserId())
				.addValue("appliedDate", request.getAppliedDate())
				.addValue("requestStatus", request.getRequestStatus())
				.addValue("typeOfRequest", request.getTypeOfRequest());
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(sql, param, holder);
//		System.out.println("holder key:" + holder.getKey());
//		System.out.println("holder keys:" + holder.getKeys());
		System.out.println("holder keys:" + holder.getKeys().get("request_id"));
//		System.out.println(holder.getKey());
//		System.out.println(holder.getKey());
		
//		request.setRequestId(holder.getKey());
//		System.out.println(request.getItineraryList());
		request.setRequestId((int)holder.getKeys().get("request_id"));
		for (Itinerary itinerary : request.getItineraryList()) {
			String itineraryInsert = "INSERT INTO public.itinerary VALUES (:requestId, :name, :genre, :author, :publication, :itineraryType);";
			SqlParameterSource itineraryPatam = new MapSqlParameterSource()
					.addValue("requestId", request.getRequestId())
					.addValue("name", itinerary.getName())
					.addValue("genre", itinerary.getGenre())
					.addValue("author", itinerary.getAuthor())
					.addValue("itineraryType", itinerary.getItinerayType())
					.addValue("publication", itinerary.getPublication());
			template.update(itineraryInsert, itineraryPatam);
		}
		return request.getRequestId();
	}

	@Override
	public String saveAttachments(List<Attachment>  attachmentList) {
		for(Attachment attachment: attachmentList) {
			String sql = "INSERT INTO public.attachments(\n"
					+ "	request_id, attachment_name, attachment_type, attachment_bytes)\n"
					+ "	VALUES (:requestId, :attachmentName, :attachmentType,:attachmentBytes);";
			try {
				SqlParameterSource param = new MapSqlParameterSource()
						.addValue("requestId", attachment.getRequestId())
						.addValue("attachmentName", attachment.getAttachmentName())
						.addValue("attachmentType", attachment.getAttachmentType())
						.addValue("attachmentBytes", attachment.getAttachmentBytes());
				KeyHolder holder = new GeneratedKeyHolder();
				template.update(sql, param, holder);
			}catch(Exception e) {
				e.printStackTrace();
				return "The db insertion failed";
			}
		}
		
		return "The attachment/s were saved successfully";
	}

	@Override
	public List<Attachment> fetchAttachments(int requestId) {
		String sql = "select * from public.attachments where request_id=:requestId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("requestId", requestId);

		List<Attachment> attachmentList = template.query(sql, param, new RowMapper<Attachment>() {
			@Override
			public Attachment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Attachment attachment = new Attachment();
				attachment.setAttachmentBytes(rs.getBytes("attachment_bytes"));
				attachment.setAttachmentName(rs.getString("attachment_name"));
				attachment.setAttachmentType(rs.getString("attachment_type"));
				attachment.setRequestId(rs.getInt("request_id"));
				return attachment;
			}

		});
		return attachmentList;
	}
}
