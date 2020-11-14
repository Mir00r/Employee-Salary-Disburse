package com.mir00r.salarydisburse.domains.employees.models.mappers

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.bankaccounts.models.mappers.BankAccountMapper
import com.mir00r.salarydisburse.domains.bankaccounts.services.BankAccountService
import com.mir00r.salarydisburse.domains.employees.models.dtos.EmployeeDto
import com.mir00r.salarydisburse.domains.common.models.mapper.BaseMapper
import com.mir00r.salarydisburse.domains.companys.models.mappers.CompanyMapper
import com.mir00r.salarydisburse.domains.companys.services.CompanyService
import com.mir00r.salarydisburse.domains.employees.models.entities.Employee
import com.mir00r.salarydisburse.domains.employees.models.enums.Grade
import com.mir00r.salarydisburse.domains.employees.repositories.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EmployeeMapper @Autowired constructor(
        private val bankAccountService: BankAccountService,
        private val companyService: CompanyService,
        private val bankAccountMapper: BankAccountMapper,
        private val companyMapper: CompanyMapper,
        private val employeeRepository: EmployeeRepository
) : BaseMapper<Employee, EmployeeDto> {

    override fun map(entity: Employee): EmployeeDto {
        val dto = EmployeeDto()
        dto.apply {
            id = entity.id
            created = entity.created
            uuid = entity.uuid
            lastUpdated = entity.lastUpdated

            name = entity.name
            phone = entity.phone
            address = entity.address
            basicSalary = entity.basicSalary
            houseRent = entity.houseRent
            medicalAllowance = entity.medicalAllowance
            gradId = Grade.get(entity.grad).id
            gradeObj = Grade.get(entity.grad)
            bankAccountId = entity.bankAccount.id
            bankAccountInfo = bankAccountMapper.map(entity.bankAccount)
            companyId = entity.company.id
            companyInfo = companyMapper.map(entity.company)
        }

        return dto
    }

    override fun map(dto: EmployeeDto, exEntity: Employee?): Employee {
        val entity = exEntity ?: Employee()
        entity.apply {
            bankAccount = bankAccountService.find(dto.bankAccountId).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK_ACCOUNT, dto.bankAccountId) }
            company = companyService.find(dto.companyId).orElseThrow { ExceptionUtil.getNotFound(Constants.COMPANY, dto.companyId) }
            grad = Grade.get(dto.gradId).id
            name = dto.name
            phone = dto.phone
            address = dto.address
            basicSalary = setEmployeeSalaryDependsGrad(dto)
            houseRent = (dto.basicSalary * 20) / 100
            medicalAllowance = (dto.basicSalary * 15) / 100
        }
        return entity
    }

    private fun setEmployeeSalaryDependsGrad(dto: EmployeeDto): Double {
        when (dto.gradId) {
            Grade.SIX.id -> return dto.basicSalary
            Grade.FIVE.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.SIX.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else dto.basicSalary + 5000.0
                return dto.basicSalary + 5000.0
            }
            Grade.FOUR.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.FIVE.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else dto.basicSalary + 5000.0
                return dto.basicSalary + 5000.0
            }
            Grade.THREE.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.FOUR.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else dto.basicSalary + 5000.0
                return dto.basicSalary + 5000.0
            }
            Grade.TWO.id -> {
                val employee = this.employeeRepository.findByGrade(Grade.THREE.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else dto.basicSalary + 5000.0
                return dto.basicSalary + 5000.0
            }
            else -> {
                val employee = this.employeeRepository.findByGrade(Grade.TWO.id)
                if (employee.isNotEmpty()) return if (employee[0].basicSalary > 0.0) employee[0].basicSalary else dto.basicSalary + 5000.0
                return dto.basicSalary + 5000.0
            }
        }

    }
}
