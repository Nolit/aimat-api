package net.nolit.dredear.entity

import net.nolit.dredear.entity.converter.LocalDateConverter
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "timelines")
class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Int = 0

    @Column(name = "user_id")
    var userId: Int = 0

    @Column(name = "created_at")
    var createdAt: Date = Date()

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    var task: Task? = null

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    var type: TimelineType? = null

    val message get() = type?.message?.format(task?.title) ?: ""
}