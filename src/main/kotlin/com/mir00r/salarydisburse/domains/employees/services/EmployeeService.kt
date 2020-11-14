package com.mir00r.salarydisburse.domains.employees.services

import com.mir00r.salarydisburse.domains.common.services.CrudService
import com.mir00r.salarydisburse.domains.employees.models.entities.Employee
import org.springframework.data.domain.Page

interface EmployeeService : CrudService<Employee> {
    fun findByCompany(companyId: Long): List<Employee>
    fun findByGrade(gradeId: Byte): List<Employee>
    fun salaryDisburse(basicSalary: Double, companyId: Long): List<Employee>
    fun search(query: String, page: Int, size: Int, companyId: Long?): Page<Employee>
}
