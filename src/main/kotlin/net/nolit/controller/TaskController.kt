package net.nolit.dredear.controller

import net.nolit.dredear.entity.Task
import net.nolit.dredear.entity.User
import net.nolit.dredear.service.TaskService
import net.nolit.dredear.controller.form.Task as TaskForm
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/tasks")
class TaskController (
        private val service: TaskService
){
    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): Task {
        return service.findById(id)
    }

    @GetMapping
    fun get(@ModelAttribute user: User, @RequestParam year: Int, @RequestParam month: Int, @RequestParam day: Int): List<Task> {
        return service.findOfUserInDate(user, LocalDate.of(year, month, day))
    }

    @PostMapping
    fun create(@ModelAttribute user: User, @ModelAttribute taskForm: TaskForm): Task {
        return service.create(taskForm.title, taskForm.amount, LocalDate.parse(taskForm.dueDate), user)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Boolean {
        service.delete(id)
        return true
    }

    @PatchMapping("/{id}/progress/{progressCount}")
    fun progress(@PathVariable id: Int, @PathVariable progressCount: Int) {
        service.progress(id, progressCount)
    }
}
