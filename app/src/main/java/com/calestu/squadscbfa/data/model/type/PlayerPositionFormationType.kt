package com.calestu.squadscbfa.data.model.type

enum class PlayerPositionFormationType(
    val index: Int,
    val playerPositionType: PlayerPositionType
) {

    GOLEIRO(1, PlayerPositionType.GOLEIRO),
    LAT_1(2, PlayerPositionType.LATERAL),
    LAT_2(3, PlayerPositionType.LATERAL),
    ZAG_1(4, PlayerPositionType.ZAGUEIRO),
    ZAG_2(5, PlayerPositionType.ZAGUEIRO),
    ZAG_3(6, PlayerPositionType.ZAGUEIRO),
    MEI_1(7, PlayerPositionType.MEIA),
    MEI_2(8, PlayerPositionType.MEIA),
    MEI_3(9, PlayerPositionType.MEIA),
    MEI_4(10, PlayerPositionType.MEIA),
    MEI_5(11, PlayerPositionType.MEIA),
    ATA_1(12, PlayerPositionType.ATACANTE),
    ATA_2(13, PlayerPositionType.ATACANTE),
    ATA_3(14, PlayerPositionType.ATACANTE);

    companion object {

        @JvmStatic
        fun getPlayerPositionFormationType(index: Int) = values().single { it.index == index}

    }

}