package com.api.boxwatch.callervolume;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CallerVolumeViewRowMapper implements RowMapper<CallerVolumeView>{

	@Override
	public CallerVolumeView mapRow(ResultSet rs, int rowNum) throws SQLException {
		CallerVolumeView cv = new CallerVolumeView();
		cv.setNumberOfCalls(rs.getInt("numberofcalls"));
		cv.setState(rs.getString("State"));
		cv.setDate(rs.getString("CallsDate"));
		
		return cv;
	}
	
}


