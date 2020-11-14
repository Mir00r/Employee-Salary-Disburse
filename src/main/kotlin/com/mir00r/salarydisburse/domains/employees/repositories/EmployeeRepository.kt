package com.mir00r.salarydisburse.domains.employees.repositories

import com.mir00r.salarydisburse.domains.employees.models.entities.Employee
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE (:q IS NULL OR e.name LIKE %:q%) AND e.deleted=FALSE")
    fun search(@Param("q") query: String, pageable: Pageable): Page<Employee>

    @Query("SELECT e FROM Employee e WHERE e.id=:id AND e.deleted=FALSE")
    fun find(@Param("id") id: Long): Optional<Employee>

    @Query("SELECT e FROM Employee e WHERE e.company.id=:companyId AND e.deleted=FALSE")
    fun findByCompany(@Param("companyId") companyId: Long): List<Employee>

    @Query("SELECT e FROM Employee e WHERE e.grad=:gradeId AND e.deleted=FALSE")
    fun findByGrade(@Param("gradeId") gradeId: Byte): List<Employee>
}
