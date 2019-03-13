package com.calestu.squadscbfa.data.model.type

import androidx.annotation.DrawableRes
import com.calestu.squadscbfa.R

enum class ClubType(val index: Int, val nameTitle: String, val tag: String, @DrawableRes val icon: Int) {

    ATHLETICOPR(1, "Athletico-PR", "CAP", R.drawable.ic_club_athleticopr),

    ATLETICOMG(2, "Atlético-MG", "CAM", R.drawable.ic_club_atleticomg),

    AVAI(3, "Avaí", "AVA", R.drawable.ic_club_avai),

    BAHIA(4, "Bahia", "BAH", R.drawable.ic_club_bahia),

    BOTAFOGO(5, "Botafogo", "BOT", R.drawable.ic_club_botafogo),

    CSA(6, "CSA", "CSA", R.drawable.ic_club_csa),

    CEARA(7, "Ceará", "CEA", R.drawable.ic_club_ceara),

    CHAPECOENSE(8, "Chapecoense", "CHA", R.drawable.ic_club_chapecoense),

    CORINTHIANS(9, "Corinthians", "COR", R.drawable.ic_club_corinthians),

    CRUZEIRO(10, "Cruzeiro", "CRU", R.drawable.ic_club_cruzeiro),

    FLAMENGO(11, "Flamengo", "FLA", R.drawable.ic_club_flamengo),

    FLUMINENSE(12, "Fluminense", "FLU", R.drawable.ic_club_fluminense),

    FORTALEZA(13, "Fortaleza", "FOR", R.drawable.ic_club_fortaleza),

    GOIAS(14, "Goiás", "GOI", R.drawable.ic_club_goias),

    GREMIO(15, "Grêmio", "GRE", R.drawable.ic_club_gremio),

    INTERNACIONAL(16, "Internacional", "INT", R.drawable.ic_club_internacional),

    PALMEIRAS(17, "Palmeiras", "PAL", R.drawable.ic_club_palmeiras),

    SANTOS(18, "Santos", "SAN", R.drawable.ic_club_santos),

    SAOPAULO(19, "São Paulo", "SAO", R.drawable.ic_club_saopaulo),

    VASCO(20, "Vasco", "VAS", R.drawable.ic_club_vasco);

    companion object {

        @JvmStatic
        fun getClub(index: Int) = values().single { it.index == index}

    }

}
