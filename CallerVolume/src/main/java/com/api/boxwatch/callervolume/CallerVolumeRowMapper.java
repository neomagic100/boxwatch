package com.api.boxwatch.callervolume;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CallerVolumeRowMapper implements RowMapper<CallerVolume>{

	@Override
	public CallerVolume mapRow(ResultSet rs, int rowNum) throws SQLException {
		CallerVolume cv = new CallerVolume();
		cv.setId(rs.getLong("cid"));
		cv.setNumCalls(rs.getInt("numberofcalls"));
		cv.setState(rs.getString("State"));
		cv.setDate(rs.getString("CallsDate"));
		
		return cv;
	}
	
}
