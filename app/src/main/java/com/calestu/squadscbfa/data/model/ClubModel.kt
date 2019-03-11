package com.calestu.squadscbfa.data.model

class ClubModel {

    enum class ClubModelEnum(val index: Int, val nameTitle: String, val tag: String) {

        ATHLETICOPR(0, "Athletico-PR", "CAP"),

        ATLETICOMG(1, "Atlético-MG", "CAM"),

        AVAI(2, "Avaí", "AVA"),

        BAHIA(3, "Bahia", "BAH"),

        BOTAFOGO(4, "Botafogo", "BOT"),

        CSA(5, "CSA", "CSA"),

        CEARA(6, "Ceará", "CEA"),

        CHAPECOENSE(7, "Chapecoense", "CHA"),

        CORINTHIANS(8, "Corinthians", "COR"),

        CRUZEIRO(9, "Cruzeiro", "CRU"),

        FLAMENGO(10, "Flamengo", "FLA"),

        FLUMINENSE(11, "Fluminense", "FLU"),

        FORTALEZA(12, "Fortaleza", "FOR"),

        GOIAS(13, "Goiás", "GOI"),

        GREMIO(14, "Grêmio", "GRE"),

        INTERNACIONAL(15, "Internacional", "INT"),

        PALMEIRAS(16, "Palmeiras", "PAL"),

        SANTOS(17, "Santos", "SAN"),

        SAOPAULO(18, "São Paulo", "SAO"),

        VASCO(19, "Vasco", "VAS");

        companion object {

            @JvmStatic
            fun getClub(index: Int) = values().single { it.index == index}

            @JvmStatic
            fun getAll() = values()

        }

    }

}