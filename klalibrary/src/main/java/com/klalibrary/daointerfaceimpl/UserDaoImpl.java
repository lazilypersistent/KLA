package com.klalibrary.daointerfaceimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.klalibrary.bean.User;
import com.klalibrary.daointerface.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public User getUserDetails(int userId) {
		String sql = "select * from public.user where user_id=:user_id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId);

		List<User> userList = template.query(sql, param, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setGender(rs.getString("gender"));
				user.setAge(rs.getString("age"));

				return user;
			}

		});
		return userList.get(0);
	}
}
