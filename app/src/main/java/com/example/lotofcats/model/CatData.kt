package com.example.lotofcats.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.String

@Parcelize
data class Cat(
    val id: String = "",
    val url: String = "",
    val categories: List<Categories> = listOf(),
    val breeds: List<Breeds> = listOf()
) : Parcelable

@Parcelize
data class Categories(
    val id: Int = 0,
    val name: String = ""
) : Parcelable

@Parcelize
data class Breeds(
    val id: String = "",
    val name: String = "",
    val temperament: String = "",
    val life_span: String = "",
    val alt_names: String = "",
    val wikipedia_url: String = "",
    val origin: String = "",
    val weight_imperial: String = "",
    val experiment: Int = 0,
    val hairless: Int = 0,
    val natural: Int = 0,
    val rare: Int = 0,
    val rex: Int = 0,
    val suppress_tail: Int = 0,
    val short_legs: Int = 0,
    val hypoallergenic: Int = 0,
    val adaptability: Int = 0,
    val affection_level: Int = 0,
    val country_code: String = "",
    val child_friendly: Int = 0,
    val dog_friendly: Int = 0,
    val energy_level: Int = 0,
    val grooming: Int = 0,
    val health_issues: Int = 0,
    val intelligence: Int = 0,
    val shedding_level: Int = 0,
    val social_needs: Int = 0,
    val stranger_friendly: Int = 0,
    val vocalisation: Int = 0
) : Parcelable