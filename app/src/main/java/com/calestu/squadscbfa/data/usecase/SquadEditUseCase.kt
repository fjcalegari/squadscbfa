package com.calestu.squadscbfa.data.usecase

import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.toSquadEditModelView
import com.calestu.squadscbfa.data.model.SquadWithPlayersDbModel
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.repository.SquadRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import com.calestu.squadscbfa.util.ext.dateTimeNow
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import java.util.*
import javax.inject.Inject

class SquadEditUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val squadRepository: SquadRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    fun createMySquad() : Single<SquadEditModelView> =
        squadRepository.insertMySquad(createSquad())
            .compose(loadSquadAfterInsert())
            .subscribeOn(schedulerProvider.diskIO())
            .observeOn(schedulerProvider.ui())

    fun loadMySquad(entryid: String) : Single<SquadEditModelView> =
        squadRepository.getMySquad(entryid)
            .map {
                it.toSquadEditModelView()
            }
//            .compose(loadSquadEditModelView())
            .subscribeOn(schedulerProvider.diskIO())
            .observeOn(schedulerProvider.ui())

//    private fun loadSquadEditModelView() = object : SingleTransformer<SquadWithPlayersDbModel, SquadEditModelView> {
//        override fun apply(upstream: Single<SquadWithPlayersDbModel>): SingleSource<SquadEditModelView> {
//            return upstream.flatMap {squadWithPlayersDbModel ->
//            }
//        }
//    }

    private fun loadSquadAfterInsert() = object : SingleTransformer<String, SquadEditModelView> {
        override fun apply(upstream: Single<String>): SingleSource<SquadEditModelView> {
            return upstream.flatMap {entryid ->
                squadRepository.getMySquad(entryid)
                    .map {it.toSquadEditModelView()}
            }
        }
    }

    private fun createSquad() : SquadEntity {
        val currentRoundType = appDataManager.getCurrentRound()
        val currentFormationType = appDataManager.getCurrentFormation()
        val userUid = appDataManager.getUserUid()
        return SquadEntity(
            entryid = UUID.randomUUID().toString(),
            owner = userUid,
            createdTime = Date().dateTimeNow(),
            updatedTime = Date().dateTimeNow(),
            round = currentRoundType,
            title = "Escalação ${currentRoundType.title}",
            formation = currentFormationType
        )
    }

}