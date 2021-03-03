package com.zalesskyi.domain.models

data class Note
@JvmOverloads
constructor(val id: String = "",
            val description: String = "",
            val imageUrl: String? = null,
            val creationDate: String = "")