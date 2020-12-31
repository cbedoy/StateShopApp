package cbedoy.stateshopapp.order

import cbedoy.stateshopapp.formatAmount
import cbedoy.stateshopapp.model.BreakdownPrice
import cbedoy.stateshopapp.model.Order
import cbedoy.stateshopapp.model.Product
import cbedoy.stateshopapp.model.ProductGroup

class OrderUseCase {
    private var currentOrder: Order = Order()

    fun reset(){
        currentOrder = Order()
    }

    fun getCurrentOrder() = currentOrder.apply {
        groupProducts.clear()
        groupProducts.addAll(products.groupBy { it.name }.map {
            ProductGroup(it.value)
        })
    }

    fun addProduct(product: Product) : Order {
        return currentOrder.apply {
            products.add(product)
            breakdownPrice.clear()
            breakdownPrice.addAll(listOf(
                BreakdownPrice("Total", total.formatAmount()),
                BreakdownPrice("Suggested tip", tip.formatAmount(), color = "#0288D1"),
                BreakdownPrice("Fee", fee.formatAmount(), color = "#4CAF50"),
                BreakdownPrice("Total to pay with tip", totalToPayWithTip.formatAmount()),
                BreakdownPrice("Total to pay without tip", totalToPayWithoutTip.formatAmount())
            ))
            groupProducts.clear()
            groupProducts.addAll(products.groupBy { it.name }.map {
                ProductGroup(it.value)
            })
        }.copy()
    }

    fun removeProduct(product: Product): Order {
        return currentOrder.apply {
            products.remove(product)
            breakdownPrice.clear()
            breakdownPrice.addAll(listOf(
                BreakdownPrice("Total", total.formatAmount()),
                BreakdownPrice("Suggested tip", tip.formatAmount(), color = "#0288D1"),
                BreakdownPrice("Fee", fee.formatAmount(), color = "#4CAF50"),
                BreakdownPrice("Total to pay with tip", totalToPayWithTip.formatAmount()),
                BreakdownPrice("Total to pay without tip", totalToPayWithoutTip.formatAmount())
            ))
            groupProducts.clear()
            groupProducts.addAll(products.groupBy { it.name }.map {
                ProductGroup(it.value)
            })
        }.copy()
    }
}