package fr.ab_dev.data.mapper

import fr.ab_dev.data.model.Organiser
import model.OrganiserDomain

fun Organiser.toDomain() =
    OrganiserDomain(
        name = this.title,
        country = country,
        imgUrl = this.img,
    )

fun List<Organiser>.toDomain() = map { organiser -> organiser.toDomain() }
