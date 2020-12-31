package cbedoy.stateshopapp.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.stateshopapp.R
import cbedoy.stateshopapp.product_list.viewholder.TypeViewHolder

class TypeAdapter(
        private val onSelectedType: (type: String) -> Unit
) : ListAdapter<String, TypeViewHolder>(
        TypeAdapterDiffUtil
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        return TypeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_option,
                        null,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = getItem(position)
        holder.reload(item)
        holder.itemView.setOnClickListener { onSelectedType(item) }
    }
}

private object TypeAdapterDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}