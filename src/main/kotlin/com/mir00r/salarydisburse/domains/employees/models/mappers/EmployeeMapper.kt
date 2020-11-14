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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EmployeeMapper @Autowired constructor(
        private val bankAccountService: BankAccountService,
        private val companyService: CompanyService,
        private val bankAccountMapper: BankAccountMapper,
        private val companyMapper: CompanyMapper
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
        }
        return entity
    }
}
