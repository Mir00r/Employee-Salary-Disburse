package com.mir00r.salarydisburse.domains.users.controllers

import com.mir00r.salarydisburse.domains.users.models.mappers.PrivilegeMapper
import com.mir00r.salarydisburse.domains.users.services.PrivilegeService
import com.mir00r.salarydisburse.domains.users.models.dtos.PrivilegeDto
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/admin/privileges")
class PrivilegeController @Autowired constructor(
        private val privilegeService: PrivilegeService,
        private val privilegeMapper: PrivilegeMapper
) {

    @PostMapping("")
    fun create(@Valid @RequestBody dto: PrivilegeDto): ResponseEntity<Any> {
        val privilege = this.privilegeService.save(this.privilegeMapper.map(dto, null))
        return ResponseEntity.ok(this.privilegeMapper.map(privilege))
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long,
               @Valid @RequestBody dto: PrivilegeDto): ResponseEntity<Any> {
        var privilege = this.privilegeService.find(id).orElseThrow { NotFoundException("Could not find privilege with id: $id") }
        privilege = this.privilegeService.save(this.privilegeMapper.map(dto, privilege))
        return ResponseEntity.ok(this.privilegeMapper.map(privilege))
    }

    @GetMapping("")
    fun search(@RequestParam("query", defaultValue = "") query: String): ResponseEntity<Any> {
        val privileges = this.privilegeService.search(query)
        return ResponseEntity.ok(privileges.map { privilege -> this.privilegeMapper.map(privilege) })
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Any> {
        val privilege = this.privilegeService.find(id).orElseThrow { NotFoundException("Couldn't find privilege with id: $id") }
        return ResponseEntity.ok(this.privilegeMapper.map(privilege))
    }

    @DeleteMapping("/{id}")
    fun softDelete(@PathVariable id: Long): ResponseEntity<Any> {
        this.privilegeService.delete(id)
        return ResponseEntity.ok().build()
    }

}
