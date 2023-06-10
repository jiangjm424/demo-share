package com.happy.ishare.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.happy.ishare.ui.databinding.LayoutShareItemBinding

internal class ShareAdapter(
    private val listener: ShareDialog.ShareClickListener,
    private val entries: List<SceneEntry> = emptyList()
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
