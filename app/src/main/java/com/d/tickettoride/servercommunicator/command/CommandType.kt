package com.d.tickettoride.servercommunicator.command

import com.google.gson.annotations.SerializedName

enum class CommandType {
    @SerializedName("C_BEGIN_PLAY") C_BEGIN_PLAY,
    @SerializedName("C_CREATE_GAME") C_CREATE_GAME,
    @SerializedName("C_JOIN_GAME") C_JOIN_GAME,
    @SerializedName("C_LOGIN") C_LOGIN,
    @SerializedName("C_POLL") C_POLL,
    @SerializedName("C_REMOVE_GAME") C_REMOVE_GAME,
    @SerializedName("C_EVENT") C_EVENT,
    @SerializedName("C_DEST_CARD") C_DEST_CARD,
    @SerializedName("C_FIRST_HAND") C_FIRST_HAND,
    @SerializedName("C_END_GAME") C_END_GAME,
    @SerializedName("C_ADVANCE_TURN") C_ADVANCE_TURN,
    @SerializedName("C_CLAIM_ROUTE") C_CLAIM_ROUTE,
    @SerializedName("C_REPLACE_ONE_FACE_UP") C_REPLACE_ONE_FACE_UP,
    @SerializedName("C_REPLACE_ALL_FACE_UP") C_REPLACE_ALL_FACE_UP,
    @SerializedName("C_ACCOUNT_FOR_THE_FACT_THAT_SOMEONE_DREW_FROM_THE_TRAIN_CAR_CARD_DRAW_PILE") C_ACCOUNT_FOR_THE_FACT_THAT_SOMEONE_DREW_FROM_THE_TRAIN_CAR_CARD_DRAW_PILE,
    @SerializedName("C_UPDATE_PLAYER_STATS") C_UPDATE_PLAYER_STATS,
    @SerializedName("S_CREATE_GAME") S_CREATE_GAME,
    @SerializedName("S_JOIN_GAME") S_JOIN_GAME,
    @SerializedName("S_LOGIN") S_LOGIN,
    @SerializedName("S_REGISTER") S_REGISTER,
    @SerializedName("S_POLL") S_POLL,
    @SerializedName("S_ASSIGN_FIRST_DEST") S_ASSIGN_FIRST_DEST,
    @SerializedName("S_ASSIGN_DEST") S_ASSIGN_DEST,
    @SerializedName("S_SEND_MESSAGE") S_SEND_MESSAGE,
    @SerializedName("S_DRAW_THREE_DESTINATION_CARDS_FROM_DRAW_PILE") S_DRAW_THREE_DESTINATION_CARDS_FROM_DRAW_PILE,
    @SerializedName("S_CLAIM_ROUTE") S_CLAIM_ROUTE,
    @SerializedName("S_DRAW_FACE_UP_TRAIN_CAR_CARD") S_DRAW_FACE_UP_TRAIN_CAR_CARD,
    @SerializedName("S_DRAW_FROM_TRAIN_PILE") S_DRAW_FROM_TRAIN_PILE,
    @SerializedName("S_END_TURN") S_END_TURN
}