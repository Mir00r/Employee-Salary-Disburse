package com.mir00r.salarydisburse.domains.common.models.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

import java.util.*

open class BaseDto : Serializable {
    @JsonProperty("id")
    @ApiModelProperty(readOnly = true)
    var id: Long? = null

    @JsonProperty("uuid")
    var uuid: String? = null

    @ApiModelProperty(notes = "It's read only", hidden = true)
    @JsonProperty(value = "created_at")
    lateinit var created: Date
        @JsonIgnore set

    @JsonProperty("last_updated")
    @ApiModelProperty(notes = "It's read only", hidden = true)
    lateinit var lastUpdated: Date
        @JsonIgnore set
}
