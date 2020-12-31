package cbedoy.stateshopapp.checkout.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import cbedoy.stateshopapp.databinding.ViewHolderCheckoutItemBinding
import cbedoy.stateshopapp.model.ProductGroup

class CheckoutProductGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ViewHolderCheckoutItemBinding.bind(itemView)

    fun reload(product: ProductGroup){
        binding.description.text = product.description
        binding.price.text = product.amount
        binding.image.load(product.dummyImage){
            crossfade(true)
        }
    }
}