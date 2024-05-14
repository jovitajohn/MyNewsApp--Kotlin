package com.jovita.mynews.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Result(val title: String,
                  val link: String,
                  val keywords: List<String>?,
                  val creator: List<String>?,
                  @JsonProperty("video_url")
                  val videoUrl: Any?,
                  val description: String,
                  val content: String?,
                  val pubDate: String,
                  @JsonProperty("full_description")
                  val fullDescription: String?,
                  @JsonProperty("image_url")
                  val imageUrl: String?,
                  @JsonProperty("source_id")
                  val sourceId: String,
                  val country: List<String>,
                  val category: List<String>,
                  val language: String):Serializable
