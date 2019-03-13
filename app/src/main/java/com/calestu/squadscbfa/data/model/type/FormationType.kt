package com.calestu.squadscbfa.data.model.type

enum class FormationType(val index: Int, val title: String, val lat: Int, val zag: Int, val mei: Int, val ata: Int) {

    FORMATION_352(1, "3-5-2", 0, 3, 5, 2),
    FORMATION_343(2, "3-4-3", 0, 3, 4, 3),
    FORMATION_442(3, "4-4-2", 2, 2, 4, 2),
    FORMATION_433(4, "4-3-3", 2, 2, 3, 3),
    FORMATION_451(5, "4-5-1", 2, 2, 5, 1);

    companion object {

        @JvmStatic
        fun getFormation(index: Int) = values().single { it.index == index}

    }

}
