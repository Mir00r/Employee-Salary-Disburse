package com.mir00r.salarydisburse.domains.employees.models.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.domains.bankaccounts.models.dtos.BankAccountDto
import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import com.mir00r.salarydisburse.domains.companys.models.dtos.CompanyDto
import com.mir00r.salarydisburse.domains.employees.models.enums.Grade
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull

@ApiModel(value = Constants.EMPLOYEE, description = "Api to Create/Read/Update/Delete " + Constants.EMPLOYEE)
class EmployeeDto : BaseDto() {

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
    @JsonProperty("grade_id")
    @ApiModelProperty(notes = "provide employee grade id number", example = "1", required = true)
    var gradId: Byte = 0

    @ApiModelProperty(notes = "Provide employee grade details information", readOnly = true)
    var gradeObj: Grade? = null
        @JsonIgnore set
        @JsonProperty("grade_obj") get

    @NotNull
    @JsonProperty("bank_account_id")
    @ApiModelProperty(notes = "provide employee bank account id number", example = "1", required = true)
    var bankAccountId: Long = 0

    @ApiModelProperty(notes = "Provide employee bank account details information", readOnly = true)
    var bankAccountInfo: BankAccountDto? = null
        @JsonIgnore set
        @JsonProperty("bank_account_info") get

    @NotNull
    @JsonProperty("company_id")
    @ApiModelProperty(notes = "provide employee bank company id number", example = "1", required = true)
    var companyId: Long = 0

    @ApiModelProperty(notes = "Provide employee company details information", readOnly = true)
    var companyInfo: CompanyDto? = null
        @JsonIgnore set
        @JsonProperty("company_info") get
}
