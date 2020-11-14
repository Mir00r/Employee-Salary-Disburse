package com.mir00r.salarydisburse.domains.employees.services.beans

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.PageAttr
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.companys.repositories.CompanyRepository
import com.mir00r.salarydisburse.domains.employees.models.entities.Employee
import com.mir00r.salarydisburse.domains.employees.models.enums.Grade
import com.mir00r.salarydisburse.domains.employees.repositories.EmployeeRepository
import com.mir00r.salarydisburse.domains.employees.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class EmployeeServiceBean @Autowired constructor(
        private val employeeRepository: EmployeeRepository,
        private val companyRepository: CompanyRepository
) : EmployeeService {

    override fun findByCompany(companyId: Long): List<Employee> {
        return this.employeeRepository.findByCompany(companyId)
    }

    override fun findByGrade(gradeId: Byte): List<Employee> {
        return this.employeeRepository.findByGrade(gradeId)
    }

    override fun salaryDisburse(basicSalary: Double, companyId: Long): List<Employee> {
        val company = this.companyRepository.find(companyId).orElseThrow { ExceptionUtil.getNotFound(Constants.COMPANY, companyId) }
        val employees = this.employeeRepository.findByCompany(company.id)
        employees.stream().sorted(Comparator.comparing(Employee::grad).reversed()).collect(Collectors.toList())

        val updatedEntity = mutableListOf<Employee>()
        for (e in employees) {
            e.basicSalary = setEmployeeSalaryDependsGrad(basicSalary, e)
            e.houseRent = (e.basicSalary * 20) / 100
            e.medicalAllowance = (e.basicSalary * 15) / 100
            updatedEntity.add(this.employeeRepository.save(e))
        }
        return updatedEntity
    }

    override fun search(query: String, page: Int, size: Int, companyId: Long?): Page<Employee> {
        return this.employeeRepository.search(query, PageAttr.getPageRequest(page, size), companyId)
    }

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

    private fun setEmployeeSalaryDependsGrad(basicSalary: Double, entity: Employee): Double {
        when (entity.grad) {
            Grade.SIX.id -> return basicSalary
            Grade.FIVE.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.SIX.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else basicSalary + 5000.0
                return basicSalary + 5000.0
            }
            Grade.FOUR.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.FIVE.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else basicSalary + 5000.0
                return basicSalary + 5000.0
            }
            Grade.THREE.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.FOUR.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else basicSalary + 5000.0
                return basicSalary + 5000.0
            }
            Grade.TWO.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.THREE.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else basicSalary + 5000.0
                return basicSalary + 5000.0
            }
            else -> {
                val employee = this.employeeRepository.findByGrade(Grade.TWO.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else basicSalary + 5000.0
                return basicSalary + 5000.0
            }
        }
    }

}
