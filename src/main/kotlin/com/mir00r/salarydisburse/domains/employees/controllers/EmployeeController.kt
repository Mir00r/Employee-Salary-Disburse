package com.mir00r.salarydisburse.domains.employees.controllers

import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.common.controller.CrudController
import com.mir00r.salarydisburse.domains.employees.models.dtos.EmployeeDto
import com.mir00r.salarydisburse.domains.employees.models.mappers.EmployeeMapper
import com.mir00r.salarydisburse.domains.employees.services.EmployeeService
import com.mir00r.salarydisburse.routes.Route
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Api(tags = ["Employees"], description = "Description about Employees")
class EmployeeController @Autowired constructor(
        private val employeeService: EmployeeService,
        private val employeeMapper: EmployeeMapper
) : CrudController<EmployeeDto> {

    @GetMapping(Route.V1.SEARCH_EMPLOYEES)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int): ResponseEntity<Page<EmployeeDto>> {
        val entities = this.employeeService.search(query, page, size)
        return ResponseEntity.ok(entities.map { this.employeeMapper.map(it) })
    }

    @GetMapping(Route.V1.FIND_EMPLOYEE)
    override fun find(@PathVariable("id") id: Long): ResponseEntity<EmployeeDto> {
        val entity = this.employeeService.find(id).orElseThrow { ExceptionUtil.getNotFound("Employee", id) }
        return ResponseEntity.ok(this.employeeMapper.map(entity))
    }

    @PostMapping(Route.V1.CREATE_EMPLOYEE)
    override fun create(@Valid @RequestBody dto: EmployeeDto): ResponseEntity<EmployeeDto> {
        val entity = this.employeeService.save(this.employeeMapper.map(dto, null))
        return ResponseEntity.ok(this.employeeMapper.map(entity))
    }

    @PatchMapping(Route.V1.UPDATE_EMPLOYEE)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @RequestBody dto: EmployeeDto): ResponseEntity<EmployeeDto> {
        var entity = this.employeeService.find(id).orElseThrow { ExceptionUtil.getNotFound("Employee", id) }
        entity = this.employeeService.save(this.employeeMapper.map(dto, entity))
        return ResponseEntity.ok(this.employeeMapper.map(entity))
    }

    @DeleteMapping(Route.V1.DELETE_EMPLOYEE)
    override fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        this.employeeService.delete(id, true)
        return ResponseEntity.ok().build()
    }

}
