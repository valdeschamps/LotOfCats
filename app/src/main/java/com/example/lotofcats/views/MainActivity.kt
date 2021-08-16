package com.example.lotofcats.views

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.TextView
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

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(0, v!!.id, 0, R.string.menu_copy)

        val textView = v as TextView
        val clipboardManager : ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.setPrimaryClip(ClipData.newPlainText("text", textView.text))
    }
}
