package com.calestu.squadscbfa.ui.module.squad.add

import com.calestu.squadscbfa.ui.module.formation.FormationFlowType

interface SquadAddNavigator {

    fun openFormation(indexSelected : Int, flowType: FormationFlowType)

    fun showPlayers(args: Pair<String, Int>)

}
