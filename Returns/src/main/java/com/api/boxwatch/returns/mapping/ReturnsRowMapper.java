package com.api.boxwatch.returns.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ReturnsRowMapper implements RowMapper<Returns> {

	@Override
	public Returns mapRow(ResultSet rs, int rowNum) throws SQLException {
		Returns cv = new Returns();
		cv.setDate(rs.getString("ReturnDate"));
		cv.setNumReturns(rs.getInt("NumberOfReturns"));

		return cv;
	}

}
