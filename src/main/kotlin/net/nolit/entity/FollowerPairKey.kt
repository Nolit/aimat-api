package net.nolit.dredear.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class FollowerPairKey: Serializable {
    var followedUserId: Int = 0
    var followingUserId: Int = 0
}