package net.nolit.dredear.repository

import net.nolit.dredear.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TaskRepository : JpaRepository<Task, Int> {
    @Query("select * from task where due_date = :dueDate AND user_id = :userId", nativeQuery = true)
    fun findByUserIdAndDueDate(@Param("userId")userId: Int, @Param("dueDate") dueDate: String): List<Task>
}