package com.gharieb.yomicepatask.core.common.extensionsFunctions

import kotlin.reflect.full.memberProperties

object ExtensionsFunctions {

    fun hasNullProperty(obj: Any): Boolean {
        return obj::class.memberProperties.any { prop ->
            try {
                prop.getter.call(obj) == null || prop.getter.call(obj) == ""
            } catch (e: Exception) {
                false
            }
        }
    }

}