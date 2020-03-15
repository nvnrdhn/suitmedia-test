package com.nvnrdhn.suitmediatest.model

object Model {
    data class Event (
        var image: Int,
        var nama: String,
        var tanggal: String
    )
    data class Guest (
        var id: Int,
        var name: String,
        var birthdate: String
    )
}

