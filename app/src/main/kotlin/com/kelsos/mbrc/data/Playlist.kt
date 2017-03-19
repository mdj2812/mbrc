package com.kelsos.mbrc.data

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.realm.RealmModel

import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name", "url")
@RealmClass
data class Playlist(
    @JsonProperty var name: String = "",
    @JsonProperty var url: String = "",
    @PrimaryKey()
    @JsonIgnore
    var id: Long = 0) : RealmModel
