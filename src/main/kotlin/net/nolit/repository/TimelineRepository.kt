package net.nolit.dredear.repository

import net.nolit.dredear.entity.Timeline
import org.springframework.data.jpa.repository.JpaRepository

interface TimelineRepository  : JpaRepository<Timeline, Int>