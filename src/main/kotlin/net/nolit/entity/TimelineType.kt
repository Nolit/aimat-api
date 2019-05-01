package net.nolit.dredear.entity

import net.nolit.dredear.entity.converter.LocalDateConverter
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "timeline_types")
class TimelineType {
    companion object {
        const val DECLARED = 1
        const val ACHIEVED = 2
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Int = 0

    var message: String = ""
}