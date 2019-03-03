package com.d.tickettoride.views

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import com.d.tickettoride.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GameActivity : AppCompatActivity(), OnMapReadyCallback, ChatFragment.OnStatsButtonPushedListener {

    private lateinit var mMap: GoogleMap
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // add in the main_drawer fragment
        drawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // find which item is selected
            val selectedID = menuItem.itemId

            // switch the menu out with the correct fragment
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment: Fragment
            if (selectedID == 0){
                fragment = StatsFragment()
            }
            else{
                fragment = ChatFragment()
            }
            fragmentTransaction.replace(R.id.content_frame, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            true
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // val mapFragment = supportFragmentManager
        //     .findFragmentById(R.id.map) as SupportMapFragment
        // mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onStatsButtonPushed(frag: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, frag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    //TODO: not excepting the interface in StatsFragment
    //This is how StatsFragment can change to
    //ChatFragment once the button has been pushed
    override fun onChatButtonPushed(frag: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, frag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
