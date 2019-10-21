package com.example.creaturemonmvvm.view.allcreatures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creaturemonmvvm.R
import com.example.creaturemonmvvm.view.creature.CreatureActivity
import com.example.creaturemonmvvm.viewmodel.AllCreaturesViewModel
import kotlinx.android.synthetic.main.activity_all_creatures.*
import org.koin.android.viewmodel.ext.android.viewModel

class AllCreaturesActivity : AppCompatActivity() {
    private val viewModel: AllCreaturesViewModel by viewModel()

    private val adapter = CreatureAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_creatures)

        setCreaturesRecyclerView()
        setAllCreaturesLiveDataObserver()
        setAddFab()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_all -> {
                viewModel.clearAllCreatures()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setCreaturesRecyclerView() {
        creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
        creaturesRecyclerView.adapter = adapter
    }

    private fun setAllCreaturesLiveDataObserver() {
        viewModel.allCreaturesLiveData.observe(this, Observer { creatures ->
            adapter.updateCreatures(creatures)
        })
    }

    private fun setAddFab() {
        addFab.setOnClickListener {
            val intent = Intent(this, CreatureActivity::class.java)
            startActivity(intent)
        }
    }
}