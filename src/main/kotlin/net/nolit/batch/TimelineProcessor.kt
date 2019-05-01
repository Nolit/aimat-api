package net.nolit.dredear.batch

import net.nolit.dredear.event.TaskDeclared
import net.nolit.dredear.repository.TaskRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class TimelineProcessor (
        private val taskRepository: TaskRepository,
        private val publisher: ApplicationEventPublisher
){
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Tokyo")
    fun declareTodayTask() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val today = formatter.format(Date())
        val taskList = taskRepository.getByDueDate(today)
        taskList.forEach { task ->
            publisher.publishEvent(TaskDeclared(this, task.userId, task.id))
        }
    }
}