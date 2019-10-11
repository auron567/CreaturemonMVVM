package com.example.creaturemonmvvm.view.allcreatures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creaturemonmvvm.R
import com.example.creaturemonmvvm.view.creature.CreatureActivity
import kotlinx.android.synthetic.main.activity_all_creatures.*

class AllCreaturesActivity : AppCompatActivity() {
    private val adapter = CreatureAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_creatures)

        setCreaturesRecyclerView()
        setAddFab()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_all -> {
                // TODO: handle action clear all
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setCreaturesRecyclerView() {
        creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
        creaturesRecyclerView.adapter = adapter
    }

    private fun setAddFab() {
        addFab.setOnClickListener {
            val intent = Intent(this, CreatureActivity::class.java)
            startActivity(intent)
        }
    }
}