package com.mir00r.salarydisburse.domains.users.services


import com.mir00r.salarydisburse.domains.users.models.entities.Role
import com.mir00r.salarydisburse.exceptions.forbidden.ForbiddenException
import com.mir00r.salarydisburse.exceptions.notfound.UserNotFoundException
import org.springframework.data.domain.Page
import java.util.*

interface RoleService {
    fun find(id: Long): Optional<Role>
    fun find(name: String): Optional<Role>
    fun findUnrestricted(name: String): Optional<Role>
    fun findByIds(roleIds: List<Long>): List<Role>
    fun save(role: Role): Role

    @Throws(ForbiddenException::class, UserNotFoundException::class)
    fun findByUser(userId: Long): List<Role>

    fun findAll(page: Int): Page<Role>
    fun delete(id: Long)
}
