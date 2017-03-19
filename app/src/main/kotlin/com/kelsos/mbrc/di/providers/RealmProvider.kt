package com.kelsos.mbrc.di.providers

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject
import javax.inject.Provider

class RealmProvider
@Inject constructor(private val context: Application) : Provider<Realm> {
  override fun get(): Realm {
    val configuration = RealmConfiguration.Builder()

        .build()
    Realm.setDefaultConfiguration(configuration)
    return Realm.getDefaultInstance()
  }
}
