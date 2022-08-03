package com.api.boxwatch.timesaved.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TimeSavedRowMapper implements RowMapper<TimeSaved> {

	@Override
	public TimeSaved mapRow(ResultSet rs, int rowNum) throws SQLException {
		TimeSaved cv = new TimeSaved();
		cv.setExpectedDeliveryDate(rs.getString("ExpectedDeliveryDate"));
		cv.setDateDamagedPackageDetected(rs.getString("DateDamagedPackageDetected"));
		cv.setDifferenceBetweenDates(rs.getInt("DifferenceBetweenDates"));

		return cv;
	}

}
