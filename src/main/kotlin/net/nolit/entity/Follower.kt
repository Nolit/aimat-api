package net.nolit.dredear.entity

import javax.persistence.*

@Entity
@Table(name = "followers")
class Follower {
    @EmbeddedId
    var followerPairKey: FollowerPairKey? = null
}