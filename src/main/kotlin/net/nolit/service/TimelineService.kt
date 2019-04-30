package net.nolit.service

import net.nolit.dredear.entity.Timeline
import net.nolit.dredear.entity.User
import net.nolit.dredear.repository.TimelineRepository
import net.nolit.dredear.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TimelineService (
        private val repository: TimelineRepository,
        private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getTimelineEveryUser(followingUserId: Int): List<User> {
        val followingUser = userRepository.findByIdOrNull(followingUserId)
        if (followingUser === null) {
            throw NullPointerException()
        }
        val userList =  userRepository.getFollowedUser(followingUser.id)
        val filteredUserList = userList.filter{user -> ! user.timelines.isEmpty()}
        val mutableUserList = filteredUserList.toMutableList()
        mutableUserList.add(followingUser)
        for (user in mutableUserList) {
            user.tasks = listOf()
        }
        return mutableUserList
    }
}