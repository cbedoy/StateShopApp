package cbedoy.stateshopapp.order

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import cbedoy.stateshopapp.CONFIG_FEE
import cbedoy.stateshopapp.CONFIG_TIP
import cbedoy.stateshopapp.formatAmount
import cbedoy.stateshopapp.model.BreakdownPrice
import cbedoy.stateshopapp.model.Order
import cbedoy.stateshopapp.model.Product

class OrderUseCaseTest {

    private lateinit var useCase: OrderUseCase
    private val TEST_PRICE = 10.0f

    @Before
    fun before(){
        useCase = OrderUseCase()
    }

    @Test
    fun `when addProduct(AnyProduct)`(){
        val anyProduct = Product(
            name = "tacos",
            amount = TEST_PRICE
        )
        val order = useCase.addProduct(
            anyProduct
        )
        assertEquals(Order(
            products = mutableListOf(
                anyProduct
            ),
            breakdownPrice = mutableListOf(
                BreakdownPrice("Total", TEST_PRICE.formatAmount()),
                BreakdownPrice("Suggested tip", (TEST_PRICE * CONFIG_TIP).formatAmount(), color = "#0288D1"),
                BreakdownPrice("Fee", (TEST_PRICE * CONFIG_FEE).formatAmount(), color = "#4CAF50"),
                BreakdownPrice("Total to pay with tip", (TEST_PRICE + (TEST_PRICE * CONFIG_TIP) + (TEST_PRICE * CONFIG_FEE)).formatAmount()),
                BreakdownPrice("Total to pay without tip", (TEST_PRICE + (TEST_PRICE * CONFIG_FEE)).formatAmount())
            )
        ), order)
    }
}