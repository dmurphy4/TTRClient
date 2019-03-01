package com.d.tickettoride.servercommunicator

enum class CommandType {
    C_BEGIN_PLAY, C_CREATE_GAME, C_JOIN_GAME, C_LOGIN, C_POLL, C_REMOVE_GAME, C_EVENT, C_DEST_CARD, C_FIRST_HAND,
    S_CREATE_GAME, S_JOIN_GAME, S_LOGIN, S_REGISTER, S_POLL, S_ASSIGN_DEST, S_SEND_MESSAGE
}