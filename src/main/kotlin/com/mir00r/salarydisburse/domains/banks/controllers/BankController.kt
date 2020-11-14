package com.mir00r.salarydisburse.domains.banks.controllers

import com.mir00r.salarydisburse.domains.banks.models.dtos.BankDto
import com.mir00r.salarydisburse.domains.banks.models.mappers.BankMapper
import com.mir00r.salarydisburse.domains.banks.services.BankService
import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.common.controller.CrudController
import com.mir00r.salarydisburse.routes.Route
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Api(tags = [Constants.BANK], description = Constants.REST_API)
class BankController @Autowired constructor(
        private val bankService: BankService,
        private val bankMapper: BankMapper
) : CrudController<BankDto> {

    @GetMapping(Route.V1.SEARCH_BANKS)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int): ResponseEntity<Page<BankDto>> {
        val entities = this.bankService.search(query, page, size)
        return ResponseEntity.ok(entities.map { this.bankMapper.map(it) })
    }

    @GetMapping(Route.V1.FIND_BANK)
    override fun find(@PathVariable("id") id: Long): ResponseEntity<BankDto> {
        val entity = this.bankService.find(id).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK, id) }
        return ResponseEntity.ok(this.bankMapper.map(entity))
    }

    @PostMapping(Route.V1.CREATE_BANK)
    override fun create(@Valid @RequestBody dto: BankDto): ResponseEntity<BankDto> {
        val entity = this.bankService.save(this.bankMapper.map(dto, null))
        return ResponseEntity.ok(this.bankMapper.map(entity))
    }

    @PatchMapping(Route.V1.UPDATE_BANK)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @RequestBody dto: BankDto): ResponseEntity<BankDto> {
        var entity = this.bankService.find(id).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK, id) }
        entity = this.bankService.save(this.bankMapper.map(dto, entity))
        return ResponseEntity.ok(this.bankMapper.map(entity))
    }

    @DeleteMapping(Route.V1.DELETE_BANK)
    override fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        this.bankService.delete(id, true)
        return ResponseEntity.ok().build()
    }

}
