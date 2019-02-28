package com.d.tickettoride.model.gameplay

import com.d.tickettoride.model.Player

data class Route(val id:Int, val city1:City, val city2:City, val length:Int, val color:RouteColor, var owner: Player?)