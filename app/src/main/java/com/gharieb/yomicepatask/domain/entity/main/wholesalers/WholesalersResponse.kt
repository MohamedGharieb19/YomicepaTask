package com.gharieb.yomicepatask.domain.entity.main.wholesalers

data class PharmaciesLinksItem(
	val zipCode: String? = null,
	val wholesalerId: Int? = null,
	val pharmacyId: Int? = null,
	val address: String? = null,
	val city: String? = null,
	val state: String? = null,
	val primary: Boolean? = null
)

data class WholesalersResponseItem(
	val name: String? = null,
	val pharmaciesLinks: List<PharmaciesLinksItem?>? = null,
	val id: Int? = null,
	val accountNumber: Any? = null
)

