package com.mir00r.salarydisburse.domains.bankaccounts.models.entities

import com.mir00r.salarydisburse.domains.banks.models.entities.Bank
import com.mir00r.salarydisburse.domains.common.models.entities.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "bankaccounts")
class BankAccount : BaseEntity() {

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = false, unique = true)
    lateinit var accountNumber: String

    @Column(nullable = false)
    var accountType: Byte = 0

    var currentBalance: Double = 0.toDouble()

    @ManyToOne
    lateinit var bank: Bank
}
