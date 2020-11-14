package com.mir00r.salarydisburse.domains.employees.services

import com.mir00r.salarydisburse.domains.employees.models.entities.Employee
import com.mir00r.salarydisburse.domains.common.services.CrudService
import java.util.*

interface EmployeeService : CrudService<Employee> {
    fun findByCompany(companyId: Long): List<Employee>
    fun findByGrade(gradeId: Byte): List<Employee>
}
