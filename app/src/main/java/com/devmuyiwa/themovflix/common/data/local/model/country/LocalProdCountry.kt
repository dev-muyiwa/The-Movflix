package com.devmuyiwa.themovflix.common.data.local.model.country

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devmuyiwa.themovflix.common.domain.model.details.ProdCountry

@Entity(tableName = LocalProdCountry.LOCAL_PRODUCTION_COUNTRY)
data class LocalProdCountry(
    @PrimaryKey val isoValue: String = "",
    val name: String = "",
) {
    companion object {
        const val LOCAL_PRODUCTION_COUNTRY = "production_country"
    }
}

fun LocalProdCountry.asDomainModel() = ProdCountry(
    isoValue = isoValue,
    name = name
)
