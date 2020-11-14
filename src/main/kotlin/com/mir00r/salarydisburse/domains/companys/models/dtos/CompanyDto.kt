package com.mir00r.salarydisburse.domains.companys.models.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.domains.bankaccounts.models.dtos.BankAccountDto
import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

@ApiModel(value = Constants.COMPANY, description = "Api to Create/Read/Update/Delete " + Constants.COMPANY)
class CompanyDto : BaseDto() {

    @NotNull
    @JsonProperty("name")
    @ApiModelProperty(notes = "provide company name", example = "X LTD", required = true)
    lateinit var name: String

    @NotNull
    @JsonProperty("phone")
    @ApiModelProperty(notes = "provide company phone number", example = "01917173733", required = true)
    lateinit var phone: String

    @JsonProperty("address")
    @ApiModelProperty(notes = "provide company address", example = "Badda")
    var address: String? = null

    @NotNull
    @JsonProperty("bank_account_id")
    @ApiModelProperty(notes = "provide company bank account id number", example = "1", required = true)
    var bankAccountId: Long = 0

    @ApiModelProperty(notes = "Provide employee bank account details information", readOnly = true)
    var bankAccountInfo: BankAccountDto? = null
        @JsonIgnore set
        @JsonProperty("bank_account_info") get
}
