package com.kelsos.mbrc.data

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
data class ConnectionSettings(var address: String? = null,
                              var port: Int = 0,
                              var name: String? = null,
                              @PrimaryKey()
                              var id: Long = 0) : RealmModel
