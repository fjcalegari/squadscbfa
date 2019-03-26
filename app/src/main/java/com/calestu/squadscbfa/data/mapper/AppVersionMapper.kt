package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.model.AppVersionResultModel
import com.calestu.squadscbfa.data.model.type.RoundType

fun AppVersionResultModel.toSaveEntity() = this.appVersionEntity.copy(
    app = appVersionRemoteModel.app,
    forceup = appVersionRemoteModel.forceup == 1,
    round = RoundType.getRound(appVersionRemoteModel.round),
    coach = appVersionRemoteModel.coach,
    CAP = appVersionRemoteModel.CAP,
    CAM = appVersionRemoteModel.CAM,
    AVA = appVersionRemoteModel.AVA,
    BAH = appVersionRemoteModel.BAH,
    BOT = appVersionRemoteModel.BOT,
    CSA = appVersionRemoteModel.CSA,
    CEA = appVersionRemoteModel.CEA,
    CHA = appVersionRemoteModel.CHA,
    COR = appVersionRemoteModel.COR,
    CRU = appVersionRemoteModel.CRU,
    FLA = appVersionRemoteModel.FLA,
    FLU = appVersionRemoteModel.FLU,
    FOR = appVersionRemoteModel.FOR,
    GOI = appVersionRemoteModel.GOI,
    GRE = appVersionRemoteModel.GRE,
    INT = appVersionRemoteModel.INT,
    PAL = appVersionRemoteModel.PAL,
    SAN = appVersionRemoteModel.SAN,
    SAO = appVersionRemoteModel.SAO,
    VAS = appVersionRemoteModel.VAS

)