package cbedoy.stateshopapp.checkout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.stateshopapp.databinding.FragmentCheckoutBinding
import cbedoy.stateshopapp.model.Order
import org.koin.android.ext.android.inject
import cbedoy.stateshopapp.BuildConfig
import cbedoy.stateshopapp.checkout.adapter.CheckoutPriceAdapter
import cbedoy.stateshopapp.checkout.adapter.CheckoutProductItemAdapter
import cbedoy.stateshopapp.common.StateFragment

class CheckoutFragment : StateFragment(){

    private val viewModel by inject<CheckoutViewModel>()

    private val binding by lazy {
        FragmentCheckoutBinding.inflate(LayoutInflater.from(context))
    }

    private val checkoutProductsAdapter = CheckoutProductItemAdapter()
    private val checkoutPriceAdapter = CheckoutPriceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override val title: String
        get() = "Checkout"

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            with(productsRecyclerView){
                layoutManager = LinearLayoutManager(context).also {
                    productsRecyclerView.addItemDecoration(
                        DividerItemDecoration(context, it.orientation)
                    )
                }
                adapter = checkoutProductsAdapter
            }
            with(breakdownRecyclerView){
                layoutManager = LinearLayoutManager(context)
                adapter = checkoutPriceAdapter
            }
        }


        viewModel.state.asLiveData().observe(viewLifecycleOwner, { state ->
            when(state){
                is CheckoutState.LoadingState -> {

                }
                is CheckoutState.ShowOrderState -> {
                    reloadOrder(state.order)
                }
            }
        })
        binding.credit.text = "Made with ❤️ by Bedoy"
        binding.version.text = "Release: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
    }

    override fun onResume() {
        super.onResume()

        viewModel.handleIntent(CheckoutIntent.LoadOrder)
    }

    private fun reloadOrder(order: Order) {
        checkoutProductsAdapter.submitList(order.groupProducts)
        checkoutPriceAdapter.submitList(order.breakdownPrice)
    }
}