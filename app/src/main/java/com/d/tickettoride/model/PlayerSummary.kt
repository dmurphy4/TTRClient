package com.d.tickettoride.model

data class PlayerSummary(val username:String, val ptsFromClaimedRoutes: Int, val ptsFromDestinations: Int,
                         val ptsReducedFromDestinations:Int, val ptsFromMostClaimedRoutes:Int, val totalPoints:Int)