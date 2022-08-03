package com.api.boxwatch.callervolume.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CallerVolumeViewRowMapper implements RowMapper<CallerVolumeView> {

	@Override
	public CallerVolumeView mapRow(ResultSet rs, int rowNum) throws SQLException {
		CallerVolumeView cv = new CallerVolumeView();
		cv.setNumberOfCalls(rs.getInt("NumberOfCalls"));
		cv.setState(rs.getString("State"));

		return cv;
	}

}
