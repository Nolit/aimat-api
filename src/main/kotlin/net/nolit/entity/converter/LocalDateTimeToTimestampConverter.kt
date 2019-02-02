package net.nolit.dredear.entity.converter

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter(autoApply = true)
class LocalDateTimeToTimestampConverter : AttributeConverter<LocalDateTime, Timestamp> {
    //データベースに保存するときに使われる(LocalDateTime→Timestamp)
    override fun convertToDatabaseColumn(localDateTime: LocalDateTime): Timestamp {
        return Timestamp.valueOf(localDateTime)
    }

    //データベースから復元するときに使われる(Timestamp→LocalDateTime)
    override fun convertToEntityAttribute(timestamp: Timestamp?): LocalDateTime? {
        return timestamp?.toLocalDateTime()
    }
}