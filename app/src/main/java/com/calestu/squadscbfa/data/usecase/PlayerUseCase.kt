package com.calestu.squadscbfa.data.usecase

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.toListPlayerItemModelView
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.repository.SquadRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import io.reactivex.Single
import javax.inject.Inject

class PlayerUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val squadRepository: SquadRepository,
    private val playerRepository: PlayerRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    fun getPlayersByPosition(playerPositionType: PlayerPositionType) : Single<List<PlayerItemModelView>> {
        return squadRepository.getPlayersByPosition(playerPositionType)
            .map { it.toListPlayerItemModelView() }
            .subscribeOn(schedulerProvider.diskIO())
            .observeOn(schedulerProvider.ui())
    }

}