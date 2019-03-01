package com.d.tickettoride.model.gameplay

import com.d.tickettoride.model.Player

data class Route(val id:Int, val city1:Int, val city2:Int, val numTracks:Int,
                 val points:Int, val color:RouteColor, var owner: Player?)