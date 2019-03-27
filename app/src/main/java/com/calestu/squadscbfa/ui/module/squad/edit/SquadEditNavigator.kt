package com.calestu.squadscbfa.ui.module.squad.edit

import com.calestu.squadscbfa.ui.module.formation.FormationFlowType

interface SquadEditNavigator {

    fun openFormation(indexSelected : Int, flowType: FormationFlowType)

    fun showPlayers(args: Pair<String, Int>)

}
