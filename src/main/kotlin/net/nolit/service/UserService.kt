package net.nolit.dredear.service

import net.nolit.dredear.entity.Follower
import net.nolit.dredear.entity.FollowerPairKey
import net.nolit.dredear.entity.User
import net.nolit.dredear.repository.FollowerRepository
import net.nolit.dredear.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.streams.toList

@Service
class UserService(
        private val repository: UserRepository,
        private val followerRepository: FollowerRepository,
        private val passwordEncoder: PasswordEncoder) {

    @Transactional
    fun create(email: String, password: String, name: String): User {
        val user = User()
        user.email = email
        user.pass = passwordEncoder.encode(password)
        user.userName = name
        return repository.save(user)
    }

    @Transactional
    fun getAll(): List<User> {
        return repository.findAll()
    }

    @Transactional
    fun findById(id: Int): User {
        return repository.getOne(id)
    }

    @Transactional
    fun getFollowedUserList(followingUserId: Int): List<User> {
        return repository.getFollowedUser(followingUserId)
    }

    @Transactional
    fun getFollowCandidatesBy(followingUserId: Int): List<User> {
        val followedUserList =  getFollowedUserList(followingUserId)
        val cannotCandidateIdList = followedUserList.stream().map { user -> user.id }.toArray().toMutableList()
        cannotCandidateIdList.add(followingUserId)
        return repository.getWithoutIdList(cannotCandidateIdList.toList() as List<Int>)
    }

    @Transactional
    fun follow(followingUserId: Int, followedUserId: Int): Follower {
        val follower = Follower()
        val followerPairKey = FollowerPairKey()
        followerPairKey.followingUserId = followingUserId
        followerPairKey.followedUserId = followedUserId
        follower.followerPairKey = followerPairKey
        return followerRepository.save(follower)
    }
}