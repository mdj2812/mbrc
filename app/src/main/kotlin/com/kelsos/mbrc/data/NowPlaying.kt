package com.kelsos.mbrc.data

import com.fasterxml.jackson.annotation.*
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonPropertyOrder("artist", "title", "path", "position")
@RealmClass
data class NowPlaying(@JsonProperty("title")
                      var title: String? = null,
                      @JsonProperty("artist")
                      var artist: String? = null,
                      @JsonProperty("path")
                      var path: String? = null,
                      @JsonProperty("position")
                      var position: Int = 0,
                      @JsonIgnore
                      @PrimaryKey()
                      var id: Long = 0) : RealmModel
