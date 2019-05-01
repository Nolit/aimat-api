package net.nolit.dredear.repository

import net.nolit.dredear.entity.Timeline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TimelineRepository  : JpaRepository<Timeline, Int> {
    @Modifying
    @Query("delete from Timeline t where t.task.id = :id")
    fun deleteByTaskId(@Param("id") id: Int)
}