package com.calestu.squadscbfa.data.source.remote.model.type

import com.calestu.squadscbfa.data.model.type.ClubType

enum class AppVersionRemoteType(val fileName: String, val clubType: ClubType) {

    VERSION("version.json", ClubType.ATHLETICOPR),
    COACH("coaches.json", ClubType.ATHLETICOPR),
    CAP("CAP.json", ClubType.ATHLETICOPR),
    CAM("CAM.json", ClubType.ATLETICOMG),
    AVA("AVA.json", ClubType.AVAI),
    BAH("BAH.json", ClubType.BAHIA),
    BOT("BOT.json", ClubType.BOTAFOGO),
    CSA("CSA.json", ClubType.CSA),
    CEA("CEA.json", ClubType.CEARA),
    CHA("CHA.json", ClubType.CHAPECOENSE),
    COR("COR.json", ClubType.CORINTHIANS),
    CRU("CRU.json", ClubType.CRUZEIRO),
    FLA("FLA.json", ClubType.FLAMENGO),
    FLU("FLU.json", ClubType.FLUMINENSE),
    FOR("FOR.json", ClubType.FORTALEZA),
    GOI("GOI.json", ClubType.GOIAS),
    GRE("GRE.json", ClubType.GREMIO),
    INT("INT.json", ClubType.INTERNACIONAL),
    PAL("PAL.json", ClubType.PALMEIRAS),
    SAN("SAN.json", ClubType.SANTOS),
    SAO("SAO.json", ClubType.SAOPAULO),
    VAS("VAS.json", ClubType.VASCO);

}
