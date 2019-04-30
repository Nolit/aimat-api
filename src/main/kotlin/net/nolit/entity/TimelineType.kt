package net.nolit.dredear.entity

import net.nolit.dredear.entity.converter.LocalDateConverter
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "timeline_types")
class TimelineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Int = 0

    var message: String = ""
}