package mappers

import model.TrackDomain
import model.TrackPresentation

fun TrackDomain.toPresentation(): TrackPresentation = TrackPresentation(this.name, this.imgUrl)
