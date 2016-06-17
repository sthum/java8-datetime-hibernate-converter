package de.sthum.hibernate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Date> {


	@Override
	public Date convertToDatabaseColumn(LocalTime lt) {
		Instant instant = lt.atDate(LocalDate.of(2016, 6, 6)).
				atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	@Override
	public LocalTime convertToEntityAttribute(Date value) {
		Instant instant = Instant.ofEpochMilli(value.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
	}
}