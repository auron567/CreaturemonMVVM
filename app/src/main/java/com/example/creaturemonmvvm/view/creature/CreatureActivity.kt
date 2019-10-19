package com.example.creaturemonmvvm.view.creature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.creaturemonmvvm.R
import com.example.creaturemonmvvm.app.toast
import com.example.creaturemonmvvm.model.AttributeStore
import com.example.creaturemonmvvm.model.AttributeType
import com.example.creaturemonmvvm.model.AttributeValue
import com.example.creaturemonmvvm.model.Avatar
import com.example.creaturemonmvvm.view.avatar.AvatarBottomDialogFragment
import com.example.creaturemonmvvm.view.avatar.AvatarListener
import com.example.creaturemonmvvm.viewmodel.CreatureViewModel
import kotlinx.android.synthetic.main.activity_creature.*
import org.koin.android.viewmodel.ext.android.viewModel

class CreatureActivity : AppCompatActivity(), AvatarListener {
    private val viewModel: CreatureViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creature)

        setUI()
        setSpinnerAdapters()
        setSpinnerListeners()
        setNameEditText()
        setClickListeners()
        setCreatureLiveDataObserver()
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

    private fun setNameEditText() {
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.nameChanged(s.toString())
            }
        })
    }

    private fun setClickListeners() {
        avatarImageView.setOnClickListener {
            val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
        }

        saveButton.setOnClickListener {
            if (viewModel.saveCreature()) {
                toast(R.string.creature_saved)
                finish()
            } else {
                toast(R.string.error_saving_creature)
            }
        }
    }

    private fun setCreatureLiveDataObserver() {
        viewModel.getCreatureLiveData().observe(this, Observer { creature ->
            hitPoints.text = creature.hitPoints.toString()
            avatarImageView.setImageResource(creature.drawable)
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