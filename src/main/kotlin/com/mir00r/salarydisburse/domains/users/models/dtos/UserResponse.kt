package com.mir00r.salarydisburse.domains.users.models.dtos

import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UserResponse : BaseDto() {
    @NotBlank
    lateinit var name: String

    @NotBlank
    @Size(min = 3)
    lateinit var username: String

    var phone: String? = null

    @Email
    var email: String? = null

    @NotBlank
    lateinit var gender: String

    lateinit var roles: List<Long>
}
