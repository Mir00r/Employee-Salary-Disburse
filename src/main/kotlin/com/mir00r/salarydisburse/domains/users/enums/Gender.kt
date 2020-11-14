package com.mir00r.salarydisburse.domains.users.enums

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
enum class Gender(
        var id: Byte,
        @JsonProperty("name_en") var labelEn: String,
        @JsonProperty("name_bn") var labelBn: String
) {

    MALE(1, "Male", "পুরুষ"),
    FEMALE(2, "Female", "মহিলা"),
    OTHER(3, "Other", "অন্যান্য"),
    UNKNOWN(4, "Unknown", "অজানা");

    companion object {

        @JvmStatic
        fun get(x: Byte): Gender {
            for (g: Gender in values()) {
                if (g.id == x)
                    return g
            }
            return OTHER
        }

        @JvmStatic
        fun get(x: String): Gender {
            for (g: Gender in values()) {
                if (g.name == x || g.labelEn == x)
                    return g
            }
            return UNKNOWN
        }

        @JvmStatic
        fun validate(x: Byte): Byte {
            if (values().any { it.id == x }) return x
            else throw ExceptionUtil.getNotFound(Gender::class.simpleName.toString(), x.toLong())
        }
    }
}
