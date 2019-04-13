package net.nolit.dredear.service

import net.nolit.dredear.entity.Task
import net.nolit.dredear.entity.Type
import net.nolit.dredear.entity.User
import net.nolit.dredear.exception.NotFoundException
import net.nolit.dredear.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class TaskService (
    private val repository: TaskRepository){

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
    }

    @Transactional(readOnly = true)
    fun findOfUserInDate(user: User, date: LocalDate): List<Task> {
        return repository.findByUserIdAndDueDate(user.id, date.toString())
    }

    @Transactional
    fun progress(id: Int, count: Int) {
        val task = repository.findById(id).get()
        task.progress += count
        repository.save(task)
    }

    @Transactional
    fun achieve(id: Int) {
        val task = repository.findById(id).get()
        task.isAchieved = true
        repository.save(task)
    }
}