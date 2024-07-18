package com.gharieb.yomicepatask.domain.entity.main.returnRequests

data class CreateReturnRequestResponse(
	val serviceFee: Any? = null,
	val returnRequestStatus: String? = null,
	val serviceType: String? = null,
	val createdAt: String? = null,
	val dateFulfilled: Any? = null,
	val disbursements: Any? = null,
	val returnRequestStatusLabel: String? = null,
	val pharmacy: Pharmacy? = null,
	val dateDispatched: Any? = null,
	val id: Int? = null,
	val preferredDate: Any? = null,
	val updatedAt: String? = null
)
