package com.example.data.tracks.remote.retrofit.response

import model.TrackData


data class GetTrackResponse(
    val tracks: List<TrackData>,
    val nbItems: Int
)