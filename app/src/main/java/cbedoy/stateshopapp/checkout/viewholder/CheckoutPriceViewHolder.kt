package cbedoy.stateshopapp.checkout.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.stateshopapp.databinding.ViewHolderPriceItemBinding
import cbedoy.stateshopapp.model.BreakdownPrice
import cbedoy.stateshopapp.setTextColorString

class CheckoutPriceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ViewHolderPriceItemBinding.bind(itemView)

    fun reload(price: BreakdownPrice){
        binding.title.text = price.title
        binding.value.text = price.description
        binding.value.setTextColorString(price.color)
    }
}