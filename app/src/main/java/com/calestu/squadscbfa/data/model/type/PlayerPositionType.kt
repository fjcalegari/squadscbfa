package com.calestu.squadscbfa.data.model.type

enum class PlayerPositionType(val pos: Int, val short: String, val tag: String) {

    GOLEIRO(1, "Goleiro", "GOL"),
    LATERAL(2, "Lateral", "LAT"),
    ZAGUEIRO(3, "Zagueiro", "ZAG"),
    MEIA(4, "Meia", "MEI"),
    ATACANTE(5, "Atacante", "ATA");

    companion object {

        @JvmStatic
        fun getPlayerPosition(pos: Int) = when(pos){
            GOLEIRO.pos -> GOLEIRO
            LATERAL.pos -> LATERAL
            ZAGUEIRO.pos -> ZAGUEIRO
            MEIA.pos -> MEIA
            ATACANTE.pos -> ATACANTE
            else -> GOLEIRO
        }

    }

}
