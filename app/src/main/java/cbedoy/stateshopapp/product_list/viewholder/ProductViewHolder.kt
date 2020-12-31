package cbedoy.stateshopapp.product_list.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import cbedoy.stateshopapp.databinding.ViewHolderProductBinding
import cbedoy.stateshopapp.formatAmount
import cbedoy.stateshopapp.model.Product

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewHolderProductBinding.bind(itemView)

    var addProductListener : (product: Product) -> Unit = {}

    fun reload(product: Product){
        binding.title.text = product.name
        binding.price.text = product.amount.formatAmount()
        binding.add.setOnClickListener {
            addProductListener(product)
        }
        binding.productImage.load(product.thumbnail){
            crossfade(true)
        }
    }
}