package com.gharieb.yomicepatask.domain.entity.main.returnRequests
data class ReturnRequestsResponse(
    val number: Int? = null,
    val last: Boolean? = null,
    val size: Int? = null,
    val numberOfElements: Int? = null,
    val totalPages: Int? = null,
    val pageable: Pageable? = null,
    val sort: Sort? = null,
    val content: List<ContentItem?>? = null,
    val first: Boolean? = null,
    val totalElements: Int? = null,
    val empty: Boolean? = null
)

data class ReturnRequest(
    val returnRequestStatus: String? = null,
    val serviceType: String? = null,
    val createdAt: Long? = null,
    val returnRequestStatusLabel: String? = null,
    val pharmacy: Pharmacy? = null,
    val id: Int? = null,
    val preferredDate: Any? = null
)

data class Pharmacy(
    val doingBusinessAs: String? = null,
    val id: Int? = null,
    val user: User? = null,
    val enabled: Boolean? = null
)

data class User(
    val role: String? = null,
    val id: Int? = null,
    val username: String? = null,
    val activated: Boolean? = null
)

data class Sort(
    val unsorted: Boolean? = null,
    val sorted: Boolean? = null,
    val empty: Boolean? = null
)

data class Pageable(
    val paged: Boolean? = null,
    val pageNumber: Int? = null,
    val offset: Int? = null,
    val pageSize: Int? = null,
    val unpaged: Boolean? = null,
    val sort: Sort? = null
)

data class ContentItem(
    val numberOfReports: Int? = null,
    val numberOfItems: Int? = null,
    val returnRequest: ReturnRequest? = null,
    val numberOfShipments: Int? = null
)