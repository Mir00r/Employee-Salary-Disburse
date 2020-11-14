package com.mir00r.salarydisburse.domains.banks.repositories

import com.mir00r.salarydisburse.domains.banks.models.entities.Bank
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BankRepository : JpaRepository<Bank, Long> {

    @Query("SELECT e FROM Bank e WHERE (:q IS NULL OR e.name LIKE %:q%) AND e.deleted=FALSE")
    fun search(@Param("q") query: String, pageable: Pageable): Page<Bank>

    @Query("SELECT e FROM Bank e WHERE e.id=:id AND e.deleted=FALSE")
    fun find(@Param("id") id: Long): Optional<Bank>

}
