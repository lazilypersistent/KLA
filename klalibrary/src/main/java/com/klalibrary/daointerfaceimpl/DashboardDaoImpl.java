package com.klalibrary.daointerfaceimpl;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.klalibrary.bean.Itinerary;
import com.klalibrary.bean.Request;
import com.klalibrary.bean.UserRequestNotes;
import com.klalibrary.daointerface.DashboardDao;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	
	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public List<Request> userRequests(int userId) {
		String sql = "select * from public.requests r, public.itinerary i where r.itinerary_id = i.itinerary_id and user_id=:user_id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId);

		List<Request> userRequestList = template.query(sql, param, new RowMapper<Request>() {
			@Override
			public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
				Request userRequest = new Request();
				userRequest.setAppliedDate(rs.getString("applied_date"));
				userRequest.setRequestId(rs.getInt("request_id"));
				userRequest.setRequestStatus(rs.getString("request_status"));
				userRequest.setTypeOfRequest(rs.getString("type_of_request"));
				Itinerary itinerary = new Itinerary();
				itinerary.setItnieraryId(rs.getInt("itinerary_id"));
				itinerary.setAuthor(rs.getString("author"));
				itinerary.setGenre(rs.getString("genre"));
				itinerary.setName(rs.getString("name"));
				itinerary.setPublication(rs.getString("publication"));
				itinerary.setItinerayType(rs.getString("itinerary_type"));
				userRequest.setItinerary(itinerary);
				return userRequest;
			}

		});
		return userRequestList;
	}

	@Override
	public List<UserRequestNotes> userRequestNotes(int itineraryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveItinerary(List<Request> requestList) {
		for (Request request: requestList) {
			String sql = "INSERT INTO public.requests (applied_date, request_status, type_of_request, user_id,itinerary_id)\n"
					+ "VALUES (:appliedDate, :requestStatus, :typeOfRequest, :userId, :itineraryId); ";
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("userId", request.getUserId())
					.addValue("appliedDate", request.getAppliedDate())
					.addValue("requestStatus", request.getRequestStatus())
					.addValue("typeOfRequest", request.getTypeOfRequest())
					.addValue("itineraryId", request.getItinerary().getItnieraryId());
			KeyHolder holder = new GeneratedKeyHolder();
			template.update(sql, param, holder);
			//request.setRequestId(holder.getKey().intValue());
		}
		
		return "Successfull";
		
//		return "Failure";
	}

	@Override
	public String saveAttachments(MultipartFile file) {
		String sql = "INSERT INTO public.attachments(\n"
				+ "	notes, remarks, attachment_name, attachment_type, attachment_bytes)\n"
				+ "	VALUES ('remarks', 'attachments', :attachmentName, :attachmentType,:attachmentBytes);";
		try {
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("remarks", "remarks")
					.addValue("notes", "notes")
					.addValue("attachmentName", file.getName())
					.addValue("attachmentType", file.getContentType())
					.addValue("attachmentBytes", file.getBytes());
			KeyHolder holder = new GeneratedKeyHolder();
			template.update(sql, param, holder);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "hello";
	}

	@Override
	public MultipartFile fetchAttachments(int requestId) {
		// TODO Auto-generated method stub
		return null;
	}

}
