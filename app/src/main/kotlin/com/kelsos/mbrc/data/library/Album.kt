package com.kelsos.mbrc.data.library

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
data class Album(@JsonProperty("artist")
                 var artist: String? = null,
                 @JsonProperty("album")
                 var album: String? = null,
                 @JsonProperty("count")
                 var count: Int = 0,
                 @JsonIgnore
                 @PrimaryKey
                 var id: Long = 0) : RealmModel
