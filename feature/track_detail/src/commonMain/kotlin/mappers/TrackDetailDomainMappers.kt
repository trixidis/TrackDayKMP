package mappers

import model.TrackDetailPresentation
import model.TrackDomain

fun TrackDomain.toDetailPresentation(): TrackDetailPresentation {
    return TrackDetailPresentation(
        name = name,
        country = country,
        imgUrl = imgUrl,
    )
}