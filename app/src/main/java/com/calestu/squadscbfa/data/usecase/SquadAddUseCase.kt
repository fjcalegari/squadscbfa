package com.calestu.squadscbfa.data.usecase

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.repository.ClubRepository
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.util.ext.flowableForUI
import com.calestu.squadscbfa.util.ext.toLiveData
import com.calestu.squadscbfa.util.ext.toState
import javax.inject.Inject

class SquadAddUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    val currentSquad = currentSquadRepository.getCurrentSquad()
//        .getCurrentSquad()
//        .toFlowable()
//        .toState()
//        .compose(flowableForUI(schedulerProvider))
//        .map { t ->  t.data!!}
//        .toLiveData()

}