package net.nolit.dredear.service

import net.nolit.dredear.entity.Task
import net.nolit.dredear.entity.Type
import net.nolit.dredear.entity.User
import net.nolit.dredear.event.TaskAchieved
import net.nolit.dredear.exception.NotFoundException
import net.nolit.dredear.repository.TaskRepository
import net.nolit.dredear.repository.TimelineRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class TaskService (
    private val repository: TaskRepository,
    private val timelineRepository: TimelineRepository,
    private val publisher: ApplicationEventPublisher
){

    @Transactional(readOnly = true)
    fun findById(id: Int): Task {
        return repository.findById(id).orElseThrow{
            throw NotFoundException()
        }
    }

    @Transactional(readOnly = true)
    fun getAll(): List<Task> {
        return repository.findAll()
    }

    @Transactional
    fun create(title: String, amount: Int, dueDate: LocalDate, user: User, type: Type): Task {
        val task = Task()
        task.title = title
        task.amount = amount
        task.dueDate = dueDate
        task.type = type
        task.userId = user.id
        return repository.save(task)
    }

    @Transactional
    fun delete(id: Int) {
        repository.deleteById(id)
        timelineRepository.deleteByTaskId(id)
    }

    @Transactional(readOnly = true)
    fun findOfUserInDate(user: User, date: LocalDate): List<Task> {
        return repository.findByUserIdAndDueDate(user.id, date.toString())
    }

    @Transactional
    fun progress(id: Int, count: Int) {
        val task = repository.findById(id).get()
        val oldIsCleared = task.isCleared
        task.progress += count
        repository.save(task)
        if (! oldIsCleared && task.isCleared) {
            publisher.publishEvent(TaskAchieved(this, task.userId, task.id))
        }
    }

    @Transactional
    fun achieve(id: Int) {
        val task = repository.findById(id).get()
        task.isAchieved = true
        repository.save(task)
        publisher.publishEvent(TaskAchieved(this, task.userId, task.id))
    }
}