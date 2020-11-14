package com.mir00r.salarydisburse.domains.companys.repositories

import com.mir00r.salarydisburse.domains.companys.models.entities.Company
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CompanyRepository : JpaRepository<Company, Long> {

    @Query("SELECT e FROM Company e WHERE (:q IS NULL OR e.name LIKE %:q%) AND e.deleted=FALSE")
    fun search(@Param("q") query: String, pageable: Pageable): Page<Company>

    @Query("SELECT e FROM Company e WHERE e.id=:id AND e.deleted=FALSE")
    fun find(@Param("id") id: Long): Optional<Company>

}
