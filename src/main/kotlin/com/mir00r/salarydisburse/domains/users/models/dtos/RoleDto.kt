package com.mir00r.salarydisburse.domains.users.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class RoleDto : BaseDto() {
    @NotBlank
    lateinit var name: String

    @NotNull
    var restricted: Boolean = true

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var privileges: List<PrivilegeDto>? = null

    @NotNull
    @NotEmpty
    @JsonProperty(value = "privilege_ids", access = JsonProperty.Access.WRITE_ONLY)
    lateinit var privilegeIds: List<Long>


}
