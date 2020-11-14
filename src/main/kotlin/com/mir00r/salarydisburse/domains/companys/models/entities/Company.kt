package com.mir00r.salarydisburse.domains.companys.models.entities

import com.mir00r.salarydisburse.domains.bankaccounts.models.entities.BankAccount
import com.mir00r.salarydisburse.domains.common.models.entities.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "companys")
class Company : BaseEntity() {

    @Column(nullable = false)
    lateinit var name: String

    var address: String? = null

    @Column(nullable = false)
    lateinit var phone: String

    @ManyToOne
    lateinit var bankAccount: BankAccount

    var totalPaidSalary: Double = 0.toDouble()
}
