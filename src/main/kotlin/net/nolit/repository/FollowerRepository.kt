package net.nolit.dredear.repository

import net.nolit.dredear.entity.Follower
import net.nolit.dredear.entity.FollowerPairKey
import org.springframework.data.jpa.repository.JpaRepository

interface FollowerRepository : JpaRepository<Follower, FollowerPairKey>