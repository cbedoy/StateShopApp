package cbedoy.stateshopapp.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.stateshopapp.product_list.viewholder.ProductViewHolder
import cbedoy.stateshopapp.R
import cbedoy.stateshopapp.model.Product

class ProductAdapter(
    private val onSelectProduct: (product: Product) -> Unit,
    private val onSelectedAddProduct: (product: Product) -> Unit
) : ListAdapter<Product, ProductViewHolder>(
    ProductListDiffUtil
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_product,
                null,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.reload(item)
        holder.itemView.setOnClickListener { onSelectProduct(item) }
        holder.addProductListener = { product ->
            onSelectedAddProduct(product)
        }
    }
}

private object ProductListDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}