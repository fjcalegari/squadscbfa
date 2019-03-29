package com.calestu.squadscbfa.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.mapper.toPlayerItemModelView
import com.calestu.squadscbfa.data.model.AppVersionResultModel
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import com.calestu.squadscbfa.data.source.remote.model.PlayerRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.type.AppVersionRemoteType
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : PlayerRepository {

    override fun getPlayers(positionType: PlayerPositionType): LiveData<List<PlayerItemModelView>> {
        return Transformations.map(localSource.getPlayers(positionType)) {
            it.map { p -> p.toPlayerItemModelView(false) }
        }
    }

    override fun loadPlayers(appVersionResultModel: AppVersionResultModel): Completable {
        return getPlayersToLoad(appVersionResultModel)
            .toFlowable()
            .flatMapIterable { it }
            .flatMapCompletable {loadPlayers(it) }
    }

    private fun loadPlayers(appVersionRemoteType: AppVersionRemoteType): Completable {
        return getRemote(appVersionRemoteType)
            .flatMapIterable { it }
            .flatMapCompletable { savePlayer(it.toEntity(appVersionRemoteType.clubType)) }
    }

    private fun getRemote(appVersionRemoteType: AppVersionRemoteType): Flowable<List<PlayerRemoteModel>> =
        remoteSource.getPlayers(appVersionRemoteType)

    override fun savePlayer(playerEntity: PlayerEntity): Completable =
        localSource.savePlayer(playerEntity)

    private fun getPlayersToLoad(appVersionResultModel: AppVersionResultModel): Single<List<AppVersionRemoteType>> {
        return Single.fromCallable {
            val list: MutableList<AppVersionRemoteType> = mutableListOf()

            if (appVersionResultModel.syncCAP) {
                list.add(AppVersionRemoteType.CAP)
            }
            if (appVersionResultModel.syncCAM) {
                list.add(AppVersionRemoteType.CAM)
            }
            if (appVersionResultModel.syncAVA) {
                list.add(AppVersionRemoteType.AVA)
            }
            if (appVersionResultModel.syncBAH) {
                list.add(AppVersionRemoteType.BAH)
            }
            if (appVersionResultModel.syncBOT) {
                list.add(AppVersionRemoteType.BOT)
            }
            if (appVersionResultModel.syncCSA) {
                list.add(AppVersionRemoteType.CSA)
            }
            if (appVersionResultModel.syncCEA) {
                list.add(AppVersionRemoteType.CEA)
            }
            if (appVersionResultModel.syncCHA) {
                list.add(AppVersionRemoteType.CHA)
            }
            if (appVersionResultModel.syncCOR) {
                list.add(AppVersionRemoteType.COR)
            }
            if (appVersionResultModel.syncCRU) {
                list.add(AppVersionRemoteType.CRU)
            }
            if (appVersionResultModel.syncFLA) {
                list.add(AppVersionRemoteType.FLA)
            }
            if (appVersionResultModel.syncFLU) {
                list.add(AppVersionRemoteType.FLU)
            }
            if (appVersionResultModel.syncFOR) {
                list.add(AppVersionRemoteType.FOR)
            }
            if (appVersionResultModel.syncGOI) {
                list.add(AppVersionRemoteType.GOI)
            }
            if (appVersionResultModel.syncGRE) {
                list.add(AppVersionRemoteType.GRE)
            }
            if (appVersionResultModel.syncINT) {
                list.add(AppVersionRemoteType.INT)
            }
            if (appVersionResultModel.syncPAL) {
                list.add(AppVersionRemoteType.PAL)
            }
            if (appVersionResultModel.syncSAN) {
                list.add(AppVersionRemoteType.SAN)
            }
            if (appVersionResultModel.syncSAO) {
                list.add(AppVersionRemoteType.SAO)
            }
            if (appVersionResultModel.syncVAS) {
                list.add(AppVersionRemoteType.VAS)
            }
            list
        }
    }

}