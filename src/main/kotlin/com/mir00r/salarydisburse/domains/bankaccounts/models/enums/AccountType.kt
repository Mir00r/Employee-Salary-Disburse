package com.mir00r.salarydisburse.domains.bankaccounts.models.enums

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import io.swagger.annotations.ApiModel

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel
enum class AccountType (
        var id: Byte,
        @JsonProperty("name_en") var labelEn: String,
        @JsonProperty("name_bn") var labelBn: String
) {

    SAVINGS(1, "Savings", "Savings"),
    CURRENT(2, "Current", "Current"),
    OTHER(3, "Other", "অন্যান্য"),
    UNKNOWN(4, "Unknown", "অজানা");

    companion object {

        @JvmStatic
        fun get(x: Byte): AccountType {
            for (g: AccountType in values()) {
                if (g.id == x)
                    return g
            }
            return OTHER
        }

        @JvmStatic
        fun get(x: String): AccountType {
            for (g: AccountType in values()) {
                if (g.name == x || g.labelEn == x)
                    return g
            }
            return UNKNOWN
        }

        @JvmStatic
        fun validate(x: Byte): Byte {
            if (values().any { it.id == x }) return x
            else throw ExceptionUtil.getNotFound(AccountType::class.simpleName.toString(), x.toLong())
        }
    }
}
