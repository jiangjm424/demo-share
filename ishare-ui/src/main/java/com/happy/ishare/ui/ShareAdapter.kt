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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.happy.ishare.ui.databinding.LayoutShareItemBinding

internal class ShareAdapter(
    private val listener: ShareDialog.ShareClickListener,
    private val entries: List<SceneEntry> = emptyList(),
) : RecyclerView.Adapter<ShareAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = LayoutShareItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val vh = VH(binding)
        binding.root.setOnClickListener {
            val pos = vh.adapterPosition
            val entry = entries.getOrNull(pos) ?: return@setOnClickListener
            listener.onClick(entry.scene, pos)
        }
        return vh
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        entries[position].apply {
            holder.binding.icon.setBackgroundResource(icon)
            holder.binding.title.setText(title)
        }
    }

    class VH(val binding: LayoutShareItemBinding) : RecyclerView.ViewHolder(binding.root)
}
