package net.nolit.dredear.event

import net.nolit.dredear.service.TimelineService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TaskDeclaredListener(
        val timelineService: TimelineService
) {
    @EventListener
    fun declaredTaskEvent(event: TaskDeclared) {
        timelineService.addDeclaredTimeline(event.userId, event.taskId)
    }
}