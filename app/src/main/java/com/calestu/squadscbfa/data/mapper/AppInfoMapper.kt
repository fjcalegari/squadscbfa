package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.util.ext.dateTimeNow
import com.google.firebase.database.DataSnapshot
import timber.log.Timber
import java.util.*

fun DataSnapshot.toAppInfoModel() : AppInfoModel {
    Timber.d("toAppInfoModel: $this")
    if (exists()) {
        return getValue(AppInfoModel::class.java) ?: AppInfoModel()
    }
    return AppInfoModel()
}

fun AppInfoModel.toEntity() = AppInfoEntity (
    entryid = entryid,
    appVersion = app,
    firstOpenTime = (if(firstOpen){Date().dateTimeNow()} else {firstOpenTime}),
    playersVersion = players,
    coachesVersion = coaches
)

fun AppInfoEntity.fromEntity() : AppInfoModel {
  return AppInfoModel(
      entryid = entryid,
      firstOpenTime = firstOpenTime,
      app = appVersion,
      players = playersVersion,
      coaches = coachesVersion
  )
}

fun mergeResults(remote: AppInfoModel, local: AppInfoModel) : AppInfoModel {
    Timber.d("mergeResults.remote: $remote")
    Timber.d("mergeResults.local: $local")
    return if (!local.emptyResult) {
        Timber.d("mergeResults: Result")
        AppInfoModel(
            app = remote.app,
            players = remote.players,
            coaches = remote.coaches,
            syncPlayers = (remote.players > local.players),
            syncCoaches = (remote.coaches > local.coaches)
        )
    } else {
        Timber.d("mergeResults: emptyResult")
        AppInfoModel(
            app = remote.app,
            players = remote.players,
            coaches = remote.coaches,
            firstOpen = true,
            syncClubs = true,
            syncPlayers = true,
            syncCoaches = true,
            emptyResult = true
        )
    }

}