package mapper

import model.OrganiserDomain
import model.OrganiserUi

fun OrganiserDomain.toPresentation(): OrganiserUi =
    OrganiserUi(
        name = this.name,
        imgUrl = this.imgUrl,
    )

fun List<OrganiserDomain>.toPresentation(): List<OrganiserUi> = this.map { it.toPresentation() }
