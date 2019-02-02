package net.nolit.dredear.entity.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.time.LocalDate

@Converter(autoApply = true)
class LocalDateConverter : AttributeConverter<LocalDate, java.sql.Date> {

    override fun convertToDatabaseColumn(localDateTime: LocalDate?): java.sql.Date? {
        return if (localDateTime == null) null else java.sql.Date.valueOf(localDateTime.plusDays(0))
    }

    override fun convertToEntityAttribute(date: java.sql.Date?): LocalDate? {
        return date?.toLocalDate()
    }
}