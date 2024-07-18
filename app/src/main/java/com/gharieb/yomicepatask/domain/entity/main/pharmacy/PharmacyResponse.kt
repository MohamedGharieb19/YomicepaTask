package com.gharieb.yomicepatask.domain.entity.main.pharmacy

data class PharmacyResponse(
	val pharmacyCompanyAddressInfo: PharmacyCompanyAddressInfo? = null,
	val pharmacyContactInfo: PharmacyContactInfo? = null,
	val pharmacy: Pharmacy? = null,
	val pharmacyMailingAddressInfo: PharmacyMailingAddressInfo? = null,
	val promoCode: Any? = null
)

data class Pharmacy(
	val dea: String? = null,
	val licenseState: String? = null,
	val companyType: String? = null,
	val npi: String? = null,
	val enabled: Boolean? = null,
	val reimbursementType: String? = null,
	val createdAt: Any? = null,
	val doingBusinessAs: String? = null,
	val wholesalersLinks: List<WholesalersLinksItem?>? = null,
	val legalBusinessName: String? = null,
	val directDepositInfo: Any? = null,
	val id: Int? = null,
	val user: User? = null,
	val licenseStateCode: String? = null,
	val updatedAt: Any? = null
)

data class PharmacyMailingAddressInfo(
	val zip: String? = null,
	val createdAt: String? = null,
	val address2: Any? = null,
	val city: String? = null,
	val address1: String? = null,
	val id: Int? = null,
	val state: String? = null,
	val updatedAt: String? = null
)

data class User(
	val createdAt: String? = null,
	val role: String? = null,
	val phoneNumber: Any? = null,
	val id: Int? = null,
	val email: String? = null,
	val updatedAt: Any? = null,
	val username: String? = null,
	val activated: Boolean? = null
)

data class PharmacyContactInfo(
	val createdAt: String? = null,
	val firstName: String? = null,
	val lastName: String? = null,
	val additionalContact2: Any? = null,
	val phone: String? = null,
	val additionalContact1: Any? = null,
	val id: Int? = null,
	val title: String? = null,
	val fax: String? = null,
	val email: String? = null,
	val updatedAt: String? = null
)

data class WholesalersLinksItem(
	val zipCode: String? = null,
	val wholesalerId: Int? = null,
	val pharmacyId: Int? = null,
	val address: String? = null,
	val city: String? = null,
	val state: String? = null,
	val primary: Boolean? = null
)

data class PharmacyCompanyAddressInfo(
	val zip: String? = null,
	val createdAt: String? = null,
	val address2: Any? = null,
	val city: String? = null,
	val address1: String? = null,
	val id: Int? = null,
	val state: String? = null,
	val updatedAt: String? = null
)

