package net.nolit.dredear.controller

import net.nolit.controller.form.UserCreateRequest
import net.nolit.controller.form.UserUpdateRequest
import net.nolit.controller.validator.UserCreateValidator
import net.nolit.dredear.entity.Follower
import net.nolit.dredear.entity.User
import net.nolit.dredear.service.UserService
import net.nolit.dredear.service.TimelineService
import net.nolit.exception.ValidationErrorException
import org.springframework.http.MediaType
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import net.nolit.dredear.controller.form.User as UserForm
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users", produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController (
        private val service: UserService,
        private val timelineService: TimelineService,
        private val createValidator: UserCreateValidator
){
    @GetMapping
    fun getAll(): List<User> {
        return service.getAll()
    }

    @GetMapping("/test")
    fun test(): User {
        return service.findById(1)
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Int, @Validated request: UserUpdateRequest): User {
        return service.update(id, request.email, request.userName, request.password)
    }

    @PostMapping
    fun create(@Validated request: UserCreateRequest, errors: Errors): User {
        if (errors.hasErrors()) {
            throw ValidationErrorException(errors)
        }
        return service.create(request.email, request.password, request.userName)
    }

    @GetMapping("/{id}/followees")
    fun getFollowees(@PathVariable id: Int): List<User> {
        return service.getFollowees(id)
    }

    @GetMapping("/{id}/followees-candidates")
    fun getFolloweeCandidates(@PathVariable id: Int): List<User> {
        return service.getFolloweeCandidatesBy(id)
    }

    @GetMapping("/me")
    fun findMe(@ModelAttribute user: User?): User? {
        user?.timelines = listOf()
        user?.tasks = listOf()
        return user
    }

    @PutMapping("/{followingUserId}/followees/{followedUserId}")
    fun follow(@PathVariable followingUserId: Int, @PathVariable followedUserId: Int): Follower {
        return service.follow(followingUserId, followedUserId)
    }

    @DeleteMapping("/{followingUserId}/followees/{followedUserId}")
    fun unfollow(@PathVariable followingUserId: Int, @PathVariable followedUserId: Int) {
        service.unfollow(followingUserId, followedUserId)
    }

    @GetMapping("/{followingUserId}/timelines")
    fun getTimeline(@PathVariable followingUserId: Int): List<Any> {
        return timelineService.getTimelineEveryUser(followingUserId)
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(createValidator)
    }
}