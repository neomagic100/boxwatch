package com.api.boxwatch.damagedpackages;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DamagedPackagesRowMapper implements RowMapper<DamagedPackages>{

	@Override
	public DamagedPackages mapRow(ResultSet rs, int rowNum) throws SQLException {
		DamagedPackages cv = new DamagedPackages();
		cv.setDate(rs.getString("date"));
		cv.setNumDetected(rs.getInt("NumberDetected"));
		cv.setNumReported(rs.getInt("NumberReported"));
		
		return cv;
	}
	
}
