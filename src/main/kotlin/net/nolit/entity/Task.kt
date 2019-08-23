package net.nolit.dredear.entity

import net.nolit.dredear.entity.converter.LocalDateConverter
import net.nolit.exception.DomainException
import java.time.LocalDate
import javax.persistence.*

@Entity
class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Int = 0

    var title: String = ""
        set(title) {
            if (!isDueDateByToday) throw DomainException("タスクの変更は前日までしてください")
            field = title
        }

    @Column(name = "user_id")
    var userId: Int = 0

    @Column(name = "due_date")
    @Convert(converter = LocalDateConverter::class)
    var dueDate: LocalDate = LocalDate.now()
        set(dueDate) {
            if (!isDueDateByToday) throw DomainException("タスクの変更は前日までしてください")
            field = dueDate
        }

    var note: String = ""

    var amount: Int = 0
        set(amount) {
            if (!isDueDateByToday) throw DomainException("タスクの変更は前日までしてください")
            field = amount
        }

    var progress: Int = 0
        set(progress) {
            if (dueDate.isEqual(LocalDate.now())) throw DomainException("タスクは当日にしてください")
            field += progress
        }

    @Column(name = "is_achieved")
    var isAchieved: Boolean = false
        set(isAchieved) {
            if (dueDate.isEqual(LocalDate.now())) throw DomainException("タスクは当日にしてください")
            field = isAchieved
        }

    @Enumerated(EnumType.STRING)
    var type: Type? = null

    val isCleared: Boolean
        get() {
            if (type === Type.ACHIEVE) {
                return isAchieved
            }
            if (type === Type.AMOUNT) {
                return progress >= amount
            }
            return false
        }

    public val isDueDateByToday: Boolean
        get() = dueDate.isBefore(LocalDate.now())
    val isDeletable: Boolean
        get() = isDueDateByToday
}

enum class Type(val type: String) {
    AMOUNT("amount"),
    TIME("time"),
    ACHIEVE("achieve")
}