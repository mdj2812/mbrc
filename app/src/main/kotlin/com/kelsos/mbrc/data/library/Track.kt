package com.kelsos.mbrc.data.library

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder("artist", "title", "src", "trackno", "disc")
@RealmClass
data class Track(@JsonProperty("artist")
                 var artist: String? = null,
                 @JsonProperty("title")
                 var title: String? = null,
                 @JsonProperty("src")
                 var src: String? = null,
                 @JsonProperty("trackno")
                 var trackno: Int = 0,
                 @JsonProperty("disc")
                 var disc: Int = 0,
                 @JsonProperty("album_artist")
                 var albumArtist: String? = null,
                 @JsonProperty("album")
                 var album: String? = null,
                 @JsonProperty("genre")
                 var genre: String? = null,
                 @JsonIgnore
                 @PrimaryKey()
                 var id: Long = 0) : RealmModel
