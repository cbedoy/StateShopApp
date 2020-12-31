package cbedoy.stateshopapp.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import org.koin.android.ext.android.inject
import cbedoy.stateshopapp.common.StateFragment
import cbedoy.stateshopapp.databinding.FragmentProductDetailBinding
import cbedoy.stateshopapp.formatAmount
import cbedoy.stateshopapp.model.Product

class ProductDetailFragment : StateFragment(){
    private val binding by lazy {
        FragmentProductDetailBinding.inflate(LayoutInflater.from(context))
    }

    override val title: String
        get() = "Detail"

    private val viewModel by inject<ProductDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Product>("product")?.let { product ->
            binding.description.text = product.description
            binding.secondaryDescription.text = product.secondaryDescription
            binding.price.text = product.amount.formatAmount()
            binding.name.text = product.name
            binding.image.load(product.thumbnail){
                crossfade(true)
            }
            binding.action.setOnClickListener {
                viewModel.handleIntent(ProductDetailIntent.AddProductIntent(
                    product = product
                ))
            }
        }
    }


}