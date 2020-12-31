package cbedoy.stateshopapp.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.stateshopapp.R
import cbedoy.stateshopapp.checkout.viewholder.CheckoutPriceViewHolder
import cbedoy.stateshopapp.model.BreakdownPrice


class CheckoutPriceAdapter : ListAdapter<BreakdownPrice, CheckoutPriceViewHolder>(
    CheckoutPriceAdapterDiffUtil
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutPriceViewHolder {
        return CheckoutPriceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_price_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CheckoutPriceViewHolder, position: Int) {
        holder.reload(getItem(position))
    }

}
private object CheckoutPriceAdapterDiffUtil : DiffUtil.ItemCallback<BreakdownPrice>() {
    override fun areItemsTheSame(oldItem: BreakdownPrice, newItem: BreakdownPrice): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: BreakdownPrice, newItem: BreakdownPrice): Boolean {
        return oldItem == newItem
    }

}