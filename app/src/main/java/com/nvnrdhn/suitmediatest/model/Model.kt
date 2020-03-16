package com.nvnrdhn.suitmediatest.model

import com.google.android.gms.maps.model.LatLng

object Model {
    data class Event (
        var image: Int,
        var nama: String,
        var tanggal: String,
        var latLng: LatLng?
    )
    data class Guest (
        var id: Int,
        var name: String,
        var birthdate: String
    )
}

