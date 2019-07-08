package net.nolit.dredear.service

import net.nolit.dredear.entity.Follower
import net.nolit.dredear.entity.FollowerPairKey
import net.nolit.dredear.entity.User
import net.nolit.dredear.repository.FollowerRepository
import net.nolit.dredear.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
    fun update(id: Int, email: String, name: String, password: String = ""): User {
        val user = repository.getOne(id)
        user.email = email
        user.userName = name
        if (password !== "") {
            user.pass = passwordEncoder.encode(password)
        }
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
    fun getFollowees(userId: Int): List<User> {
        return repository.getFollowedUser(userId)
    }

    @Transactional
    fun getFolloweeCandidatesBy(userId: Int): List<User> {
        val followedUserList =  getFollowees(userId)
        val cannotCandidateIdList = followedUserList.stream().map { user -> user.id }.toArray().toMutableList()
        cannotCandidateIdList.add(userId)
        return repository.getWithoutIdList(cannotCandidateIdList.toList() as List<Int>)
    }

    @Transactional
    fun follow(followerId: Int, followeeId: Int): Follower {
        val follower = Follower()
        val followerPairKey = FollowerPairKey()
        followerPairKey.followingUserId = followerId
        followerPairKey.followedUserId = followeeId
        follower.followerPairKey = followerPairKey
        return followerRepository.save(follower)
    }

    @Transactional
    fun unfollow(followerId: Int, followeeId: Int) {
        val followerPairKey = FollowerPairKey()
        followerPairKey.followingUserId = followerId
        followerPairKey.followedUserId = followeeId
        followerRepository.deleteById(followerPairKey)
    }
}