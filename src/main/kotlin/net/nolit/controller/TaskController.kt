package net.nolit.dredear.controller

import net.nolit.controller.form.TaskCreateRequest
import net.nolit.dredear.entity.Task
import net.nolit.dredear.entity.Type
import net.nolit.dredear.entity.User
import net.nolit.dredear.service.TaskService
import org.springframework.validation.annotation.Validated
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
    fun create(@ModelAttribute user: User, @Validated @ModelAttribute request: TaskCreateRequest): Task {
        return service.create(request.title, request.amount, LocalDate.parse(request.dueDate), user, Type.valueOf(request.type))
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

    @PutMapping("/{id}/achieved")
    fun achieve(@PathVariable id: Int) {
        service.achieve(id)
    }
}
