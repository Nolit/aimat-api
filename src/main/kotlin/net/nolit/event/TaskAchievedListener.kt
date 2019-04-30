package net.nolit.dredear.event

import net.nolit.service.TimelineService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TaskAchievedListener (
        val timelineService: TimelineService
){

    @EventListener
    fun achievedTaskEvent(event: TaskAchieved) {
        timelineService.addAchievedTimeline(event.userId, event.taskId)
    }
}