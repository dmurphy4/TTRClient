package com.d.tickettoride.servercommunicator

import com.d.tickettoride.servercommunicator.command.CommandType
import com.d.tickettoride.servercommunicator.command.client.*
import com.google.gson.Gson

class CommandData(var type:CommandType, var data:String) {
    fun execute() {
        when (type) {
            CommandType.C_CREATE_GAME -> Gson().fromJson(data, CCreateGameCommand::class.java).execute()
            CommandType.C_BEGIN_PLAY -> Gson().fromJson(data, CBeginPlayCommand::class.java).execute()
            CommandType.C_REMOVE_GAME -> Gson().fromJson(data, CRemoveGameCommand::class.java).execute()
            CommandType.C_DEST_CARD -> Gson().fromJson(data, CChooseDestCardCommand::class.java).execute()
            CommandType.C_FIRST_HAND -> Gson().fromJson(data, CFirstHandCommand::class.java).execute()
            CommandType.C_EVENT -> Gson().fromJson(data, CChatCommand::class.java).execute()
            CommandType.C_UPDATE_PLAYER_STATS -> Gson().fromJson(data, CChangeStatsCommand::class.java).execute()
            CommandType.C_ADVANCE_TURN -> Gson().fromJson(data, CAdvanceTurnCommand::class.java).execute()
            CommandType.C_REPLACE_ONE_FACE_UP -> Gson().fromJson(data, CReplaceOneFaceUpCommand::class.java).execute()
            CommandType.C_ACCOUNT_FOR_THE_FACT_THAT_SOMEONE_DREW_FROM_THE_TRAIN_CAR_CARD_DRAW_PILE -> Gson().fromJson(data, CAccountForTrainCardDrawCommand::class.java).execute()
            CommandType.C_CLAIM_ROUTE -> Gson().fromJson(data, CClaimRouteCommand::class.java).execute()
            CommandType.C_REPLACE_ALL_FACE_UP -> Gson().fromJson(data, CReplaceAllFaceUpCommand::class.java).execute()
            CommandType.C_END_GAME -> Gson().fromJson(data, CEndGameCommand::class.java).execute()
            CommandType.C_ACCOUNT_FOR_DESTINATION_DRAW -> Gson().fromJson(data, CAccountForDestinationDrawCommand::class.java).execute()
            else -> println("Leilani Fonbuena")
        }
    }
}