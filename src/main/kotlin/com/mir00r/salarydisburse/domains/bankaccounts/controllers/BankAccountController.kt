package com.mir00r.salarydisburse.domains.bankaccounts.controllers

import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.bankaccounts.models.dtos.BankAccountDto
import com.mir00r.salarydisburse.domains.bankaccounts.models.mappers.BankAccountMapper
import com.mir00r.salarydisburse.domains.bankaccounts.services.BankAccountService
import com.mir00r.salarydisburse.domains.common.controller.CrudController
import com.mir00r.salarydisburse.routes.Route
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Api(tags = ["BankAccounts"], description = "Description about BankAccounts")
class BankAccountController @Autowired constructor(
        private val bankAccountService: BankAccountService,
        private val bankAccountMapper: BankAccountMapper
) : CrudController<BankAccountDto> {

    @GetMapping(Route.V1.SEARCH_BANKACCOUNTS)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int): ResponseEntity<Page<BankAccountDto>> {
        val entities = this.bankAccountService.search(query, page, size)
        return ResponseEntity.ok(entities.map { this.bankAccountMapper.map(it) })
    }

    @GetMapping(Route.V1.FIND_BANKACCOUNT)
    override fun find(@PathVariable("id") id: Long): ResponseEntity<BankAccountDto> {
        val entity = this.bankAccountService.find(id).orElseThrow { ExceptionUtil.getNotFound("BankAccount", id) }
        return ResponseEntity.ok(this.bankAccountMapper.map(entity))
    }

    @PostMapping(Route.V1.CREATE_BANKACCOUNT)
    override fun create(@Valid @RequestBody dto: BankAccountDto): ResponseEntity<BankAccountDto> {
        val entity = this.bankAccountService.save(this.bankAccountMapper.map(dto, null))
        return ResponseEntity.ok(this.bankAccountMapper.map(entity))
    }

    @PatchMapping(Route.V1.UPDATE_BANKACCOUNT)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @RequestBody dto: BankAccountDto): ResponseEntity<BankAccountDto> {
        var entity = this.bankAccountService.find(id).orElseThrow { ExceptionUtil.getNotFound("BankAccount", id) }
        entity = this.bankAccountService.save(this.bankAccountMapper.map(dto, entity))
        return ResponseEntity.ok(this.bankAccountMapper.map(entity))
    }

    @DeleteMapping(Route.V1.DELETE_BANKACCOUNT)
    override fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        this.bankAccountService.delete(id, true)
        return ResponseEntity.ok().build()
    }

}
