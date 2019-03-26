package com.calestu.squadscbfa.data.model.type

enum class RoundType(val round: Int, val title: String) {

    ROUND_1(1, "Rodada 1"),
    ROUND_2(2, "Rodada 2"),
    ROUND_3(3, "Rodada 3"),
    ROUND_4(4, "Rodada 4"),
    ROUND_5(5, "Rodada 5"),
    ROUND_6(6, "Rodada 6"),
    ROUND_7(7, "Rodada 7"),
    ROUND_8(8, "Rodada 8"),
    ROUND_9(9, "Rodada 9"),
    ROUND_10(10, "Rodada 10"),
    ROUND_11(11, "Rodada 11"),
    ROUND_12(12, "Rodada 12"),
    ROUND_13(13, "Rodada 13"),
    ROUND_14(14, "Rodada 14"),
    ROUND_15(15, "Rodada 15"),
    ROUND_16(16, "Rodada 16"),
    ROUND_17(17, "Rodada 17"),
    ROUND_18(18, "Rodada 18"),
    ROUND_19(19, "Rodada 19"),
    ROUND_20(20, "Rodada 20"),
    ROUND_21(21, "Rodada 21"),
    ROUND_22(22, "Rodada 22"),
    ROUND_23(23, "Rodada 23"),
    ROUND_24(24, "Rodada 24"),
    ROUND_25(25, "Rodada 25"),
    ROUND_26(26, "Rodada 26"),
    ROUND_27(27, "Rodada 27"),
    ROUND_28(28, "Rodada 28"),
    ROUND_29(29, "Rodada 29"),
    ROUND_30(30, "Rodada 30"),
    ROUND_31(31, "Rodada 31"),
    ROUND_32(32, "Rodada 32"),
    ROUND_33(33, "Rodada 33"),
    ROUND_34(34, "Rodada 34"),
    ROUND_35(35, "Rodada 35"),
    ROUND_36(36, "Rodada 36"),
    ROUND_37(37, "Rodada 37"),
    ROUND_38(38, "Rodada 38");

    companion object {

        @JvmStatic
        fun getRound(round: Int): RoundType {
            return values().single {
                it.round == round
            }
        }

    }

}
