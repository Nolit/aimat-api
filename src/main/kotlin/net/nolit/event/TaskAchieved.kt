package net.nolit.dredear.event

import org.springframework.context.ApplicationEvent

class TaskAchieved(source: Any, userId: Int, taskId: Int): ApplicationEvent(source) {
    val userId = userId
    val taskId = taskId
}