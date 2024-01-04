package com.gochoa.componentcatalog

data class CheckInfo(
    val title: String,
    val selected: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)
