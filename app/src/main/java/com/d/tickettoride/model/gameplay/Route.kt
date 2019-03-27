package com.d.tickettoride.model.gameplay

data class Route(val id:Int, val city1:Int, val city2:Int, val numTracks:Int, val color:RouteColor,
                 var owner:String?, var companionId:Int, val lat1: Float, val lat2: Float,
                 val lon1: Float, val lon2: Float)