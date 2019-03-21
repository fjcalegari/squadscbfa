package com.calestu.squadscbfa.data.source.local

import androidx.room.TypeConverter
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.model.type.RoundType
import java.util.*

class LocalDbConverter {

    companion object {

        @TypeConverter @JvmStatic
        fun fromFormationType(value: FormationType): Int {
            return value.index
        }

        @TypeConverter @JvmStatic
        fun toFormationType(value: Int): FormationType {
            return FormationType.getFormation(value)
        }

        @TypeConverter @JvmStatic
        fun fromClubType(value: ClubType): Int {
            return value.index
        }

        @TypeConverter @JvmStatic
        fun toClubType(value: Int): ClubType {
            return ClubType.getClub(value)
        }

        @TypeConverter @JvmStatic
        fun fromPlayerPositionType(value: PlayerPositionType): Int {
            return value.pos
        }

        @TypeConverter @JvmStatic
        fun toPlayerPositionType(value: Int): PlayerPositionType {
            return PlayerPositionType.getPlayerPosition(value)
        }

        @TypeConverter @JvmStatic
        fun fromRoundType(value: RoundType): Int {
            return value.round
        }

        @TypeConverter @JvmStatic
        fun toRoundType(value: Int): RoundType {
            return RoundType.getRound(value)
        }

        @TypeConverter @JvmStatic
        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @TypeConverter @JvmStatic
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }

    }

}