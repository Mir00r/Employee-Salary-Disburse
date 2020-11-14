package com.mir00r.salarydisburse.domains.employees.services.beans

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.domains.employees.models.entities.Employee
import com.mir00r.salarydisburse.domains.employees.repositories.EmployeeRepository
import com.mir00r.salarydisburse.domains.employees.services.EmployeeService
import com.mir00r.salarydisburse.commons.PageAttr
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeServiceBean @Autowired constructor(
        private val employeeRepository: EmployeeRepository
) : EmployeeService {

    override fun search(query: String, page: Int, size: Int): Page<Employee> {
        return this.employeeRepository.search(query, PageAttr.getPageRequest(page, size))
    }

    override fun save(entity: Employee): Employee {
        return this.employeeRepository.save(entity)
    }

    override fun find(id: Long): Optional<Employee> {
        return this.employeeRepository.find(id)
    }

    override fun findByUUIDIncludingDeleted(uuId: String): Optional<Employee> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long, softDelete: Boolean) {
        if (softDelete) {
            val entity = this.find(id).orElseThrow { ExceptionUtil.getNotFound(Constants.EMPLOYEE, id) }
            entity.isDeleted = true
            this.employeeRepository.save(entity)
        }
        this.employeeRepository.deleteById(id)
    }

}
