/*
 * Copyright 2023 The IShare Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.happy.ishare.ui

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.happy.ishare.ShareSDK
import com.happy.ishare.core.Scene
import com.happy.ishare.ui.databinding.LayoutDialogShareBinding

class ShareDialog : DialogFragment() {

    private var shareListener: ShareClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        shareListener = context as? ShareClickListener
    }

    private lateinit var binding: LayoutDialogShareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ShareDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = LayoutDialogShareBinding.inflate(inflater, container, false)
        setupWindowStyle(dialog?.window)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shareList.adapter = ShareAdapter(
            WrappedShareClickListener(),
            ShareSDK.allSupportShareScene().mapNotNull {
                sceneResMap[it]
            },
        )
        binding.shareList.layoutManager = DisableScrollLinearLayoutManager(requireContext(), 4)
    }

    private fun setupWindowStyle(w: Window?) = w?.let {
        it.setBackgroundDrawableResource(android.R.color.transparent)
        it.decorView.setPadding(20, 0, 20, 0)
        val wlp = it.attributes
        wlp.gravity = Gravity.BOTTOM
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT
        it.attributes = wlp
    }

    interface ShareClickListener {
        fun onClick(scene: Scene, pos: Int)
    }
    private inner class WrappedShareClickListener : ShareClickListener {
        override fun onClick(scene: Scene, pos: Int) {
            dismiss()
            shareListener?.onClick(scene, pos)
        }
    }
}
