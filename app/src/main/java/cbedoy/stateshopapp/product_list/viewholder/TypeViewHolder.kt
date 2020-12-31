package cbedoy.stateshopapp.product_list.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.stateshopapp.databinding.ViewHolderOptionBinding

class TypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ViewHolderOptionBinding.bind(itemView)

    fun reload(type: String){
        binding.title.text = type
    }
}