package com.mir00r.salarydisburse.seeds

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.commons.utils.PasswordUtil
import com.mir00r.salarydisburse.domains.users.enums.Gender
import com.mir00r.salarydisburse.domains.users.enums.Roles
import com.mir00r.salarydisburse.domains.users.models.entities.Privilege
import com.mir00r.salarydisburse.domains.users.models.entities.Role
import com.mir00r.salarydisburse.domains.users.models.entities.User
import com.mir00r.salarydisburse.domains.users.repositories.RoleRepository
import com.mir00r.salarydisburse.domains.users.services.PrivilegeService
import com.mir00r.salarydisburse.domains.users.services.RoleService
import com.mir00r.salarydisburse.domains.users.services.UserService
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
@Component
class UserSeed @Autowired constructor(
        private val privilegeService: PrivilegeService,
        private val roleService: RoleService,
        private val roleRepository: RoleRepository,
        private val userService: UserService
) {
    @Value("\${admin.username}")
    lateinit var adminUsername: String

    @Value("\${admin.password}")
    lateinit var adminPass: String

    @Value("\${admin.phone}")
    lateinit var adminPhone: String

    @Value("\${admin.email}")
    lateinit var adminEmail: String

    @Value("\${auth.method}")
    lateinit var authMethod: String


    @EventListener(ContextRefreshedEvent::class)
    fun onBootUp() {
        this.seedPrivileges()
    }

    private fun seedPrivileges() {
        if (!this.privilegeService.empty()) return

        val privileges = this.createPrivilegesIfNotExists()

        for (r in Roles.values()) {
            val role = Role()
            role.name = r.name

            when (r.labelEn) {
                Roles.ADMIN.labelEn -> {
                    role.privileges = privileges
                }
                Roles.USER.labelEn -> {
                    role.restricted = false
                    role.privileges = getPrivilegesNameList(Privilege.Privileges.ACCESS_USER_RESOURCES.name)
                }
            }
            this.roleService.save(role)
        }

        // create admin user
        val user = User()
        user.name = "Admin"
        user.username = this.adminUsername
        user.password = PasswordUtil.encryptPassword(this.adminPass, PasswordUtil.EncType.BCRYPT_ENCODER, null)
        user.phone = this.adminPhone
        user.email = this.adminEmail
        user.gender = Gender.MALE.name
        user.roles = ArrayList()
        user.roles.add(this.roleService.find(Roles.ADMIN.name).orElseThrow { NotFoundException("Could not assign admin role to admin as it's not found!") })

        this.userService.save(user)
    }

    private fun createPrivilegesIfNotExists(): List<Privilege> {
        val privileges: MutableList<Privilege> = ArrayList()

        Privilege.Privileges.values().forEach {
            val privilege = this.privilegeService.find(it.toString())
            if (!privilege.isPresent) {
                privileges.add(this.privilegeService.save(Privilege(it.name, it.label)))
            }
        }
        return privileges
    }

    private fun getPrivilegesNameList(privilegeName: String): List<Privilege> {
        val userPrivilege = this.privilegeService.find(privilegeName).orElseThrow {
            ExceptionUtil.getNotFound(Constants.PRIVILEGES, privilegeName)
        }
        return listOf(userPrivilege)
    }
}
