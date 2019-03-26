package com.calestu.squadscbfa.data.model

import com.calestu.squadscbfa.data.entity.AppVersionEntity
import com.calestu.squadscbfa.data.source.remote.model.AppVersionRemoteModel

data class AppVersionResultModel(
    val appVersionEntity: AppVersionEntity,
    val appVersionRemoteModel: AppVersionRemoteModel
) {

    val firstOpen: Boolean
        get() = appVersionEntity.app == 0

    val syncCoach: Boolean
        get() = appVersionEntity.coach < appVersionRemoteModel.coach

    val syncCAP: Boolean
        get() = appVersionEntity.CAP < appVersionRemoteModel.CAP

    val syncCAM: Boolean
        get() = appVersionEntity.CAM < appVersionRemoteModel.CAM

    val syncAVA: Boolean
        get() = appVersionEntity.AVA < appVersionRemoteModel.AVA

    val syncBAH: Boolean
        get() = appVersionEntity.BAH < appVersionRemoteModel.BAH

    val syncBOT: Boolean
        get() = appVersionEntity.BOT < appVersionRemoteModel.BOT

    val syncCSA: Boolean
        get() = appVersionEntity.CSA < appVersionRemoteModel.CSA

    val syncCEA: Boolean
        get() = appVersionEntity.CEA < appVersionRemoteModel.CEA

    val syncCHA: Boolean
        get() = appVersionEntity.CHA < appVersionRemoteModel.CHA

    val syncCOR: Boolean
        get() = appVersionEntity.COR < appVersionRemoteModel.COR

    val syncCRU: Boolean
        get() = appVersionEntity.CRU < appVersionRemoteModel.CRU

    val syncFLA: Boolean
        get() = appVersionEntity.FLA < appVersionRemoteModel.FLA

    val syncFLU: Boolean
        get() = appVersionEntity.FLU < appVersionRemoteModel.FLU

    val syncFOR: Boolean
        get() = appVersionEntity.FOR < appVersionRemoteModel.FOR

    val syncGOI: Boolean
        get() = appVersionEntity.GOI < appVersionRemoteModel.GOI

    val syncGRE: Boolean
        get() = appVersionEntity.GRE < appVersionRemoteModel.GRE

    val syncINT: Boolean
        get() = appVersionEntity.INT < appVersionRemoteModel.INT

    val syncPAL: Boolean
        get() = appVersionEntity.PAL < appVersionRemoteModel.PAL

    val syncSAN: Boolean
        get() = appVersionEntity.SAN < appVersionRemoteModel.SAN

    val syncSAO: Boolean
        get() = appVersionEntity.SAO < appVersionRemoteModel.SAO

    val syncVAS: Boolean
        get() = appVersionEntity.VAS < appVersionRemoteModel.VAS

}