package com.gharieb.yomicepatask.domain.entity.main.items

data class AddItemRequest(
	val dosage: String? = null,
	val requestType: String? = null,
	val strength: String? = null,
	val description: String? = null,
	val packageSize: String? = null,
	val lotNumber: String? = null,
	val ndc: String? = null,
	val partialQuantity: String? = null,
	val manufacturer: String? = null,
	val fullQuantity: String? = null,
	val name: String? = null,
	val expirationDate: String? = null,
	val status: String? = null
)

