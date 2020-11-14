package com.mir00r.salarydisburse.domains.banks.models.entities

import com.mir00r.salarydisburse.domains.common.models.entities.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "banks")
class Bank : BaseEntity() {

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = false)
    lateinit var branchName: String

    var address: String? = null

    var balance: Double = 0.toDouble()
}
