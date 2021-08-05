package com.example.lotofcats.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.lotofcats.R
import com.example.lotofcats.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.nav_graph)
        val appBarConfiguration = AppBarConfiguration(findNavController(R.id.nav_host_fragment).graph)
        binding.toolbar.setupWithNavController(findNavController(R.id.nav_host_fragment), appBarConfiguration)
    }
}
