package com.calestu.squadscbfa.data.model.type

enum class PlayerPositionFormationType(
    val index: Int
) {

    GOLEIRO(1),
    LAT_1(2),
    LAT_2(3),
    ZAG_1(4),
    ZAG_2(5),
    ZAG_3(6),
    MEI_1(7),
    MEI_2(8),
    MEI_3(9),
    MEI_4(10),
    MEI_5(11),
    ATA_1(12),
    ATA_2(13),
    ATA_3(14);

    companion object {

        @JvmStatic
        fun getPlayerPositionFormationType(index: Int) = values().single { it.index == index}

    }

}