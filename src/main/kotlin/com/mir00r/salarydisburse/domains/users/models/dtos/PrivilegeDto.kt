package com.mir00r.salarydisburse.domains.users.models.dtos

import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

class PrivilegeDto : BaseDto() {
    @NotBlank
    lateinit var name: String
    @NotBlank
    lateinit var label: String

    @NotBlank.List
    @JsonProperty("access_urls")
    lateinit var accessUrls: List<String>
}
