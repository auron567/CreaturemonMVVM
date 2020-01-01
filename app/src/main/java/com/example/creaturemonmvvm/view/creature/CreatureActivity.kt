package com.example.creaturemonmvvm.view.creature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.creaturemonmvvm.R
import com.example.creaturemonmvvm.app.CreaturemonApplication
import com.example.creaturemonmvvm.app.toast
import com.example.creaturemonmvvm.databinding.ActivityCreatureBinding
import com.example.creaturemonmvvm.model.AttributeStore
import com.example.creaturemonmvvm.model.AttributeType
import com.example.creaturemonmvvm.model.AttributeValue
import com.example.creaturemonmvvm.model.Avatar
import com.example.creaturemonmvvm.view.avatar.AvatarBottomDialogFragment
import com.example.creaturemonmvvm.view.avatar.AvatarListener
import com.example.creaturemonmvvm.viewmodel.CreatureViewModel
import kotlinx.android.synthetic.main.activity_creature.*
import javax.inject.Inject

class CreatureActivity : AppCompatActivity(), AvatarListener {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CreatureViewModel

    private lateinit var binding: ActivityCreatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CreaturemonApplication).appComponent
            .creatureComponent().create().inject(this)

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CreatureViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_creature)
        binding.viewmodel = viewModel

        setUI()
        setSpinnerAdapters()
        setSpinnerListeners()
        setClickListeners()
        setLiveDataObservers()
    }

    private fun setUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (viewModel.isDrawableSelected()) {
            hideTapLabel()
        }
    }

    private fun setSpinnerAdapters() {
        intelligenceSpinner.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.INTELLIGENCE
        )
        strengthSpinner.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.STRENGTH
        )
        enduranceSpinner.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.ENDURANCE
        )
    }

    private fun setSpinnerListeners() {
        intelligenceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.attributeSelected(AttributeType.INTELLIGENCE, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        strengthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.attributeSelected(AttributeType.STRENGTH, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        enduranceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.attributeSelected(AttributeType.ENDURANCE, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setClickListeners() {
        avatarImageView.setOnClickListener {
            val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
        }
    }

    private fun setLiveDataObservers() {
        viewModel.creatureLiveData.observe(this, Observer { creature ->
            hitPoints.text = creature.hitPoints.toString()
            avatarImageView.setImageResource(creature.drawable)
        })

        viewModel.saveLiveData.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { saved ->
                if (saved) {
                    toast(R.string.creature_saved)
                    finish()
                } else {
                    toast(R.string.error_saving_creature)
                }
            }
        })
    }

    override fun avatarClicked(avatar: Avatar) {
        viewModel.drawableSelected(avatar.drawable)
        hideTapLabel()
    }

    private fun hideTapLabel() {
        tapLabel.visibility = View.INVISIBLE
    }
}