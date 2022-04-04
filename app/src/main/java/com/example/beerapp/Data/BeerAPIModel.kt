package com.example.beerapp.Data

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Method


data class BeerAPI(
    val id: Long,
    val name: String? = null,
    val tagline: String? = null,

    @SerializedName
        ("first_brewed")
    val firstBrewed: String? = null,

    val description: String? = null,

    @SerializedName
        ("image_url")
    val imageURL: String? = null,

    val abv: Double? = null,
    val ibu: Double? = null,

    @SerializedName
        ("target_fg")
    val targetFg: Double? = null,

    @SerializedName
        ("target_og")
    val targetOg: Double? = null,

    val ebc: Double? = null,
    val srm: Double? = null,
    val ph: Double? = null,

    @SerializedName
        ("attenuation_level")
    val attenuationLevel: Double? = null,

    val volume: BoilVolume? = null,

    @SerializedName
        ("boil_volume")
    val boilVolume: BoilVolume? = null,

    val method: Method? = null,
    val ingredients: Ingredients? = null,

    @SerializedName
        ("food_pairing")
    val foodPairing: List<String>? = null,

    @SerializedName
        ("brewers_tips")
    val brewersTips: String? = null,

    @SerializedName
        ("contributed_by")
    val contributedBy: String? = null
)



data class BoilVolume(
    val value: Double? = null,
    val unit: String? = null
)

data class Ingredients(
    val malt: List<Malt>? = null,
    val hops: List<Hop>? = null,
    val yeast: String? = null
)

data class Hop(
    val name: String? = null,
    val amount: BoilVolume? = null,
    val add: String? = null,
    val attribute: String? = null
)

data class Malt(
    val name: String? = null,
    val amount: BoilVolume? = null
)

data class Method(
    @SerializedName
        ("mash_temp")
    val mashTemp: List<MashTemp>? = null,

    val fermentation: Fermentation? = null,
    val twist: String? = null
)

data class Fermentation(
    val temp: BoilVolume? = null
)

data class MashTemp(
    val temp: BoilVolume? = null,
    val duration: Double? = null
)
