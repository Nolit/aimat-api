package net.nolit.dredear.repository

import net.nolit.dredear.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User

    @Query("select u from User u, Follower f where u.id =  f.followerPairKey.followedUserId and f.followerPairKey.followingUserId = :followingUserId")
    fun getFollowedUser(@Param("followingUserId") followingUserId: Int): List<User>

    @Query("select u from User u where u.id not in :withoutIdList")
    fun getWithoutIdList(@Param("withoutIdList") withoutIdList: List<Int>): List<User>
}