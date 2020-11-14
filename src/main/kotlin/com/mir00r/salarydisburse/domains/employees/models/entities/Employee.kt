package com.mir00r.salarydisburse.domains.employees.models.entities

import com.mir00r.salarydisburse.domains.bankaccounts.models.entities.BankAccount
import com.mir00r.salarydisburse.domains.common.models.entities.base.BaseEntity
import com.mir00r.salarydisburse.domains.companys.models.entities.Company
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "employees")
class Employee : BaseEntity() {

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = false)
    var grad: Byte = 0

    var address: String? = null

    @Column(nullable = false)
    lateinit var phone: String

    @ManyToOne
    lateinit var company: Company

    @ManyToOne
    lateinit var bankAccount: BankAccount
}
