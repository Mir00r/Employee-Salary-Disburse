package com.mir00r.salarydisburse.domains.users.enums

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class BloodGroup(
        var id: Byte,
        @JsonProperty("name_en") var labelEn: String,
        @JsonProperty("name_bn") var labelBn: String
) {

    A_POSITIVE(1, "A+", "এ+"),
    A_NEGATIVE(2, "A-", "এ−"),
    B_POSITIVE(3, "B+", "বি+"),
    B_NEGATIVE(4, "B-", "বি−"),
    AB_POSITIVE(5, "AB+", "এবি+"),
    AB_NEGATIVE(6, "AB-", "এবি−"),
    O_POSITIVE(7, "O+", "ও+"),
    O_NEGATIVE(8, "O-", "ও−"),
    UNKNOWN(9, "Unknown", "অজানা");

    companion object {
        @JvmStatic
        fun get(x: Byte?): BloodGroup {
            if (x == null) return UNKNOWN

            for (m: BloodGroup in values()) {
                if (m.id == x)
                    return m
            }
            return UNKNOWN
        }

        @JvmStatic
        fun get(x: String): BloodGroup {
            for (m: BloodGroup in values()) {
                if (m.name == x || m.labelEn == x || m.labelBn == x)
                    return m
            }
            return UNKNOWN
        }

        @JvmStatic
        fun validate(x: Byte): Byte {
            if (values().any { it.id == x }) return x
            else throw ExceptionUtil.getNotFound(BloodGroup::class.simpleName.toString(), x.toLong())
        }
    }
}
