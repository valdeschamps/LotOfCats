package com.example.lotofcats.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.lotofcats.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)
        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.nav_graph)
        val appBarConfiguration = AppBarConfiguration(findNavController(R.id.nav_host_fragment).graph)
        toolbar.setupWithNavController(findNavController(R.id.nav_host_fragment), appBarConfiguration)
    }
}
