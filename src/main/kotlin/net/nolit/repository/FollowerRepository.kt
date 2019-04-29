package net.nolit.dredear.repository

import net.nolit.dredear.entity.Follower
import net.nolit.dredear.entity.FollowerPairKey
import net.nolit.dredear.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FollowerRepository : JpaRepository<Follower, Int> {
}