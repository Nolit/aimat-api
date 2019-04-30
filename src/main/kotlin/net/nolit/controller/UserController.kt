package net.nolit.dredear.controller

import net.nolit.dredear.entity.Follower
import net.nolit.dredear.entity.Timeline
import net.nolit.dredear.entity.User
import net.nolit.dredear.service.UserService
import net.nolit.service.TimelineService
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

    @GetMapping("/{id}/followed-users")
    fun getFriends(@PathVariable id: Int): List<User> {
        return service.getFollowedUserList(id)
    }

    @GetMapping("/{id}/friend-candidates")
    fun getFriendCandidates(@PathVariable id: Int): List<User> {
        return service.getFollowCandidatesBy(id)
    }

    @GetMapping("/sign-in-user")
    fun findSignInUser(@ModelAttribute user: User?): User? {
        return user
    }

    @PutMapping("/{followingUserId}/followed-users/{followedUserId}")
    fun follow(@PathVariable followingUserId: Int, @PathVariable followedUserId: Int): Follower {
        return service.follow(followingUserId, followedUserId)
    }
    @GetMapping("/{followingUserId}/timelines")
    fun getTimeline(@PathVariable followingUserId: Int): List<Any> {
        return timelineService.getTimelineEveryUser(followingUserId)
    }
}