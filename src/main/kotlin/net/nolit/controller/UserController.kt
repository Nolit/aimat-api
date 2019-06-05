package net.nolit.dredear.controller

import net.nolit.dredear.entity.Follower
import net.nolit.dredear.entity.User
import net.nolit.dredear.service.UserService
import net.nolit.dredear.service.TimelineService
import net.nolit.dredear.controller.form.User as UserForm
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
class UserController (
        private val service: UserService,
        private val timelineService: TimelineService
){

    @GetMapping
    fun getAll(): List<User> {
        return service.getAll()
    }

    @PostMapping
    fun create(request: HttpServletRequest): User {
        return service.create(request.getParameter("email"), request.getParameter("password"), request.getParameter("userName"))
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
}