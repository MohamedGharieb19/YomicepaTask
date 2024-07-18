package com.gharieb.yomicepatask.domain.entity.authentication

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	val userInfo: UserInfo? = null,
	val token: String? = null
)

data class UserInfo(
	val createdAt: String? = null,
	val role: String? = null,
	val phoneNumber: Any? = null,
	val id: Int? = null,
	val email: String? = null,
	val updatedAt: Any? = null,
	@field:SerializedName("username")
	val userName: String? = null,
	val activated: Boolean? = null
)
