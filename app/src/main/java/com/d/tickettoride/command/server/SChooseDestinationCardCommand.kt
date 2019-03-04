package com.d.tickettoride.command.server

import com.d.tickettoride.model.gameplay.DestinationCard

data class SChooseDestinationCardCommand(var player:String, var chosenCards:List<DestinationCard>)