package net.nolit.repository

import net.nolit.dredear.entity.TimelineType
import org.springframework.data.jpa.repository.JpaRepository

interface TimelineTypeRepository  : JpaRepository<TimelineType, Int>