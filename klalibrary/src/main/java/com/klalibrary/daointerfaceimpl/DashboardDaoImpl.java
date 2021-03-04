package com.klalibrary.daointerfaceimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.klalibrary.bean.Itinerary;
import com.klalibrary.bean.Request;
import com.klalibrary.bean.UserRequestNotes;
import com.klalibrary.daointerface.DashboardDao;

public class DashboardDaoImpl implements DashboardDao {
	
	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public List<Request> userRequests(int userId) {
		String sql = "select * from public.itinerary where user_id=:user_id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId);

		List<Request> userRequestList = template.query(sql, param, new RowMapper<Request>() {
			@Override
			public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
				Request userRequest = new Request();
				userRequest.setAppliedDate(rs.getString("applied_date"));
				userRequest.setRequestId(rs.getInt("request_id"));
				userRequest.setRequestStatus(rs.getString("request_status"));
				userRequest.setRequestType(rs.getString("request_type"));
				userRequest.setStatus(rs.getString("status"));
				userRequest.setTypeOfRequest(rs.getString("type_of_request"));
				Itinerary itinerary = new Itinerary();
				itinerary.setItnieraryId(rs.getInt("itinerary_id"));
				itinerary.setAuthor(rs.getString("author"));
				itinerary.setGenre(rs.getString("genre"));
				itinerary.setName(rs.getString("name"));
				itinerary.setPublication(rs.getString("publication"));
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

}
