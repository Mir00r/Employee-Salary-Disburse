package com.mir00r.salarydisburse.domains.users.models.mappers

import com.mir00r.salarydisburse.domains.users.models.dtos.RoleDto
import com.mir00r.salarydisburse.domains.users.models.entities.Role
import com.mir00r.salarydisburse.domains.users.services.PrivilegeService
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RoleMapper @Autowired constructor(
        val privilegeMapper: PrivilegeMapper,
        val privilegeService: PrivilegeService
) {

    fun map(role: Role): RoleDto {
        val dto = RoleDto()
        dto.id = role.id
        dto.created = role.created
        dto.lastUpdated = role.lastUpdated

        dto.name = role.name
        dto.restricted = role.restricted
        dto.privileges = role.privileges?.map { privilege -> this.privilegeMapper.map(privilege) }
        return dto
    }

    fun map(dto: RoleDto, exRole: Role?): Role {
        val role = exRole ?: Role()

        role.name = dto.name
        role.restricted = dto.restricted
        role.privileges = dto.privilegeIds.map { privilegeId ->
            this.privilegeService.find(privilegeId)
                    .orElseThrow { NotFoundException("Could not find privilege with id: $privilegeId") }
        }
        return role
    }

}
