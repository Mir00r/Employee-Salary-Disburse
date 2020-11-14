package com.mir00r.salarydisburse.domains.bankaccounts.repositories

import com.mir00r.salarydisburse.domains.bankaccounts.models.entities.BankAccount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BankAccountRepository : JpaRepository<BankAccount, Long> {

    @Query("SELECT e FROM BankAccount e WHERE (:q IS NULL OR e.name LIKE %:q%) AND e.deleted=FALSE")
    fun search(@Param("q") query: String, pageable: Pageable): Page<BankAccount>

    @Query("SELECT e FROM BankAccount e WHERE e.id=:id AND e.deleted=FALSE")
    fun find(@Param("id") id: Long): Optional<BankAccount>

}
