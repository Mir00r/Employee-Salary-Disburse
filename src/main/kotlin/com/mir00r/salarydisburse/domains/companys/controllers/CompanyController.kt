package com.mir00r.salarydisburse.domains.companys.controllers

import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.companys.models.dtos.CompanyDto
import com.mir00r.salarydisburse.domains.companys.models.mappers.CompanyMapper
import com.mir00r.salarydisburse.domains.companys.services.CompanyService
import com.mir00r.salarydisburse.domains.common.controller.CrudController
import com.mir00r.salarydisburse.routes.Route
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Api(tags = ["Companys"], description = "Description about Companys")
class CompanyController @Autowired constructor(
        private val companyService: CompanyService,
        private val companyMapper: CompanyMapper
) : CrudController<CompanyDto> {

    @PatchMapping(Route.V1.COMPANYS_SALARY_TRANSFER)
    fun salaryTransfer(
            @RequestParam("amount", required = true, defaultValue = "5000") amount: Double,
            @RequestParam("company_id", required = true, defaultValue = "1") companyId: Long): ResponseEntity<CompanyDto> {
        val entity = this.companyService.salaryTransfer(companyId, amount)
        return ResponseEntity.ok(this.companyMapper.map(entity))
    }

    @GetMapping(Route.V1.SEARCH_COMPANYS)
    fun search(@RequestParam("q", defaultValue = "") query: String,
               @RequestParam("page", defaultValue = "0") page: Int,
               @RequestParam("size", defaultValue = "10") size: Int,
               @RequestParam("bank_account_id", required = false) bankAccountId: Long?): ResponseEntity<Page<CompanyDto>> {
        val entities = this.companyService.search(query, page, size, bankAccountId)
        return ResponseEntity.ok(entities.map { this.companyMapper.map(it) })
    }

    //    @GetMapping(Route.V1.SEARCH_COMPANYS)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int): ResponseEntity<Page<CompanyDto>> {
        val entities = this.companyService.search(query, page, size)
        return ResponseEntity.ok(entities.map { this.companyMapper.map(it) })
    }

    @GetMapping(Route.V1.FIND_COMPANY)
    override fun find(@PathVariable("id") id: Long): ResponseEntity<CompanyDto> {
        val entity = this.companyService.find(id).orElseThrow { ExceptionUtil.getNotFound("Company", id) }
        return ResponseEntity.ok(this.companyMapper.map(entity))
    }

    @PostMapping(Route.V1.CREATE_COMPANY)
    override fun create(@Valid @RequestBody dto: CompanyDto): ResponseEntity<CompanyDto> {
        val entity = this.companyService.save(this.companyMapper.map(dto, null))
        return ResponseEntity.ok(this.companyMapper.map(entity))
    }

    @PatchMapping(Route.V1.UPDATE_COMPANY)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @RequestBody dto: CompanyDto): ResponseEntity<CompanyDto> {
        var entity = this.companyService.find(id).orElseThrow { ExceptionUtil.getNotFound("Company", id) }
        entity = this.companyService.save(this.companyMapper.map(dto, entity))
        return ResponseEntity.ok(this.companyMapper.map(entity))
    }

    @DeleteMapping(Route.V1.DELETE_COMPANY)
    override fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        this.companyService.delete(id, true)
        return ResponseEntity.ok().build()
    }

}
