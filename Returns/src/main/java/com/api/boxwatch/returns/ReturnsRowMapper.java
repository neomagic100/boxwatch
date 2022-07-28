package com.api.boxwatch.returns;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ReturnsRowMapper implements RowMapper<Returns>{

	@Override
	public Returns mapRow(ResultSet rs, int rowNum) throws SQLException {
		Returns cv = new Returns();
		cv.setDate(rs.getString("Date"));
		cv.setNumReturns(rs.getInt("NumberOfReturns"));
		
		return cv;
	}
	
}
