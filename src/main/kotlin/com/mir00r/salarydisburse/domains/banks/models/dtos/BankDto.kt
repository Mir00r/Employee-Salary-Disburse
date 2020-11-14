package com.mir00r.salarydisburse.domains.banks.models.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

@ApiModel(value = Constants.BANK, description = "Api to Create/Read/Update/Delete " + Constants.BANK)
class BankDto: BaseDto() {

    @NotNull
    @JsonProperty("name")
    @ApiModelProperty(notes = "provide bank name", example = "Brac", required = true)
    lateinit var name: String

    @NotNull
    @JsonProperty("branch_name")
    @ApiModelProperty(notes = "provide bank branch name", example = "Badda", required = true)
    lateinit var branchName: String

    @JsonProperty("address")
    @ApiModelProperty(notes = "provide bank address", example = "Brac")
    var address: String? = null

    @NotNull
    @JsonProperty("balance")
    @ApiModelProperty(notes = "provide bank balance amount", example = "100000.0", required = true)
    var balance: Double = 0.0
}
