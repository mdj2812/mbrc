package com.kelsos.mbrc.data.library

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder("genre", "count")
@RealmClass
data class Genre(@JsonProperty("genre")
                 var genre: String? = null,
                 @JsonProperty("count")
                 var count: Int = 0,
                 @JsonIgnore
                 @PrimaryKey()
                 var id: Long = 0) : RealmModel
