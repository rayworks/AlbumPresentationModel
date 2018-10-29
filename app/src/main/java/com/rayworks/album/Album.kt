package com.rayworks.album

data class Album(
    var title: String,
    var artist: String,
    var isClassical: Boolean = false,
    var composer: String = ""
)