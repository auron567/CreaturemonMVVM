package com.example.creaturemonmvvm.view.avatar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.creaturemonmvvm.R
import com.example.creaturemonmvvm.model.Avatar
import com.example.creaturemonmvvm.model.AvatarStore
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_dialog_fragment_avatar.*
import java.lang.ClassCastException

class AvatarBottomDialogFragment : BottomSheetDialogFragment(), AvatarListener {
    private lateinit var callback: AvatarListener

    companion object {
        fun newInstance(): AvatarBottomDialogFragment {
            return AvatarBottomDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_dialog_fragment_avatar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatarRecyclerView.layoutManager = GridLayoutManager(context, 3)
        avatarRecyclerView.adapter = AvatarAdapter(AvatarStore.AVATARS, this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try {
            callback = activity as AvatarListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${activity.toString()} must implement AvatarListener")
        }
    }

    override fun avatarClicked(avatar: Avatar) {
        callback.avatarClicked(avatar)
    }
}