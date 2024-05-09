package com.jovita.mynews

data class News( val status: String,
                 val totalResults: Long,
                 val results: List<Result>,
                 val nextPage: String)

