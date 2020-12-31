package cbedoy.stateshopapp.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.stateshopapp.R
import cbedoy.stateshopapp.checkout.viewholder.CheckoutProductGroupViewHolder
import cbedoy.stateshopapp.model.ProductGroup

class CheckoutProductItemAdapter : ListAdapter<ProductGroup, CheckoutProductGroupViewHolder>(
    AdapterDiffUtil
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutProductGroupViewHolder {
        return CheckoutProductGroupViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_checkout_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CheckoutProductGroupViewHolder, position: Int) {
        holder.reload(getItem(position))
    }

}

private object AdapterDiffUtil : DiffUtil.ItemCallback<ProductGroup>() {
    override fun areItemsTheSame(oldItem: ProductGroup, newItem: ProductGroup): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: ProductGroup, newItem: ProductGroup): Boolean {
        return oldItem == newItem
    }

}