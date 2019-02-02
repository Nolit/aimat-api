package net.nolit.dredear.entity

import net.nolit.dredear.entity.converter.LocalDateConverter
import java.time.LocalDate
import javax.persistence.*

@Entity
class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Int = 0

    var title: String = ""

    @Column(name = "user_id")
    var userId: Int = 0

    @Column(name = "due_date")
    @Convert(converter = LocalDateConverter::class)
    var dueDate: LocalDate = LocalDate.now()

    var note: String = ""

    var amount: Int = 0

    var progress: Int = 0

    @Enumerated(EnumType.STRING)
    var type: Type? = null
}

enum class Type(val type: String) {
    AMOUNT("amount"),
    TIME("time")
}