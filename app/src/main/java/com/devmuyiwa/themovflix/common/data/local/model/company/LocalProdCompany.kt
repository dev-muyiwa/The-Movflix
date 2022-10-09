package com.devmuyiwa.themovflix.common.data.local.model.company

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devmuyiwa.themovflix.common.domain.model.details.ProdCompany

@Entity(tableName = LocalProdCompany.LOCAL_PRODUCTION_COMPANY)
data class LocalProdCompany(
    @PrimaryKey(autoGenerate = false) val companyId: Long = 0,
    val logoUrl: String = "",
    val name: String = "",
    val country: String = "",
) {
    companion object {
        const val LOCAL_PRODUCTION_COMPANY = "production_company"
    }
}

fun LocalProdCompany.asDomainModel() = ProdCompany(
    companyId = companyId,
    logoUrl = logoUrl,
    name = name,
    country = country
)
