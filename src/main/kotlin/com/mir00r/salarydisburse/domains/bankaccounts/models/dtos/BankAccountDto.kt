package com.mir00r.salarydisburse.domains.bankaccounts.models.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.domains.bankaccounts.models.enums.AccountType
import com.mir00r.salarydisburse.domains.banks.models.dtos.BankDto
import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import com.mir00r.salarydisburse.domains.employees.models.enums.Grade
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

@ApiModel(value = Constants.BANK_ACCOUNT, description = "Api to Create/Read/Update/Delete " + Constants.BANK_ACCOUNT)
class BankAccountDto : BaseDto() {

    @NotNull
    @JsonProperty("name")
    @ApiModelProperty(notes = "provide bank account name", example = "Mr. X", required = true)
    lateinit var name: String

    @NotNull
    @JsonProperty("account_number")
    @ApiModelProperty(notes = "provide bank unique account number", example = "101011", required = true)
    lateinit var accountNumber: String

    @NotNull
    @JsonProperty("account_type")
    @ApiModelProperty(notes = "provide bank address", example = "1", required = true)
    var accountType: Byte = 1

    @ApiModelProperty(notes = "Provide bank account type details information", readOnly = true)
    var accountTypeObj: AccountType? = null
        @JsonIgnore set
        @JsonProperty("account_type_obj") get

    @NotNull
    @JsonProperty("current_balance")
    @ApiModelProperty(notes = "provide bank current balance amount", example = "1000.0", required = true)
    var currentBalance: Double = 0.0

    @NotNull
    @JsonProperty("bank_id")
    @ApiModelProperty(notes = "provide bank id number", example = "1", required = true)
    var bankId: Long = 0

    @ApiModelProperty(notes = "Provide bank details information", readOnly = true)
    var bankInfo: BankDto? = null
        @JsonIgnore set
        @JsonProperty("bank_info") get
}
