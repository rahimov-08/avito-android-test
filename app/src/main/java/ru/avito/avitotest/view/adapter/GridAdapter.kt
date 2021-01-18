package ru.avito.avitotest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ru.avito.avitotest.R
import ru.avito.avitotest.service.model.GridNumber
import ru.avito.avitotest.view.callback.OnRemoveButtonListener

class GridAdapter(private val removeButtonListener: OnRemoveButtonListener) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    private val differ = AsyncListDiffer<GridNumber>(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val model = differ.currentList[position]
        holder.bind(model)
    }

    override fun getItemCount() = differ.currentList.size

    fun submitData(newData: List<GridNumber>) {
        differ.submitList(newData)
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val valueTextView: TextView = itemView.findViewById(R.id.card_number)
        private val removeButton: MaterialButton = itemView.findViewById(R.id.card_remove_button)

        fun bind(model: GridNumber) {
            valueTextView.text = model.value
            removeButton.setOnClickListener {
                removeButtonListener.removeButtonClicked(model)
            }
        }
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GridNumber>() {

            override fun areItemsTheSame(oldItem: GridNumber, newItem: GridNumber): Boolean {
                return oldItem.value == newItem.value
            }

            override fun areContentsTheSame(oldItem: GridNumber, newItem: GridNumber): Boolean {
                return oldItem == newItem
            }
        }
    }
}