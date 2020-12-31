package cbedoy.stateshopapp.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import cbedoy.stateshopapp.R
import cbedoy.stateshopapp.common.StateFragment
import cbedoy.stateshopapp.databinding.FragmentProductListBinding
import cbedoy.stateshopapp.product_list.adapter.ProductAdapter
import cbedoy.stateshopapp.product_list.adapter.TypeAdapter
import cbedoy.stateshopapp.support.SupportState
import zendesk.messaging.MessagingActivity


class ProductListFragment : StateFragment(){

    private val viewModel by inject<ProductListViewModel>()

    override val title: String
        get() = "Products"

    private val productAdapter = ProductAdapter(
            onSelectProduct = { product ->
                val bundleOf = bundleOf("product" to product)
                findNavController().navigate(R.id.action_move_from_list_to_detail, bundleOf, navOptions)
            },
            onSelectedAddProduct = { product ->
                viewModel.handleIntent(
                    ProductListIntent.AddProduct(product = product)
                )
            }
    )

    private val typesAdapter = TypeAdapter(
            onSelectedType = { type ->
                viewModel.handleIntent(ProductListIntent.FilterProduct(type = type))
            }
    )

    var navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .build()

    private val binding by lazy {
        FragmentProductListBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerViewTypes){
            adapter = typesAdapter
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
        with(binding.recyclerView){
            adapter = productAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        binding.cartAction.setOnClickListener {
            findNavController().navigate(R.id.action_move_from_list_to_checkout)
        }
        binding.supportAction.setOnClickListener {
            viewModel.handleIntent(
                    ProductListIntent.RequestSupport
            )
        }

        lifecycleScope.launch {
            viewModel.productListState.collect { state ->
                when (state) {
                    is ProductListState.LoadedProducts -> {
                        productAdapter.submitList(state.productList)
                    }
                    is ProductListState.ShowLoader -> {
                        binding.progressBar.isVisible = true
                    }
                    is ProductListState.HideLoader -> {
                        binding.progressBar.isVisible = false
                    }
                    is ProductListState.LoadedTypes -> {
                        typesAdapter.submitList(state.types)
                    }
                    is ProductListState.ShowSupport -> {
                        MessagingActivity.builder()
                                .withEngines(state.engines)
                                .show(requireActivity(), state.configuration)
                    }
                }
            }
            viewModel.supportState.collect { state ->
                when(state){
                    is SupportState.None -> {

                    }
                    is SupportState.StartChatWith -> {
                        MessagingActivity.builder()
                                .withEngines(state.engines)
                                .show(requireActivity(), state.configuration)
                    }
                }
            }
        }
        viewModel.handleIntent(ProductListIntent.LoadProducts)
    }
}