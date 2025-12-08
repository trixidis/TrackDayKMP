package fr.ab_dev.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Organiser(
    val id: String,
    val color: String,
    val img: String,
    val title: String,
    val country: String,
    val country2: String,
    val trackDayNB: Int,
    val url: String,
    val likeIt: Boolean,
    val followIt: Boolean
)