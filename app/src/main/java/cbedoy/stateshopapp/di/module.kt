package cbedoy.stateshopapp.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import cbedoy.stateshopapp.order.OrderUseCase
import cbedoy.stateshopapp.order.OrderViewModel
import cbedoy.stateshopapp.product_detail.ProductDetailFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import cbedoy.stateshopapp.checkout.CheckoutViewModel
import cbedoy.stateshopapp.product_detail.ProductDetailViewModel
import cbedoy.stateshopapp.product_list.*
import cbedoy.stateshopapp.product_list.repository.PokeProductListRepository
import cbedoy.stateshopapp.product_list.repository.RickAndMortyRepository
import cbedoy.stateshopapp.product_list.service.PokeProductListService
import cbedoy.stateshopapp.product_list.service.RickAndMortyService
import cbedoy.stateshopapp.product_list.usecase.PokeProductListUseCase
import cbedoy.stateshopapp.product_list.usecase.RickAndMortyUseCase
import cbedoy.stateshopapp.retrofit.buildRetrofitInstance
import cbedoy.stateshopapp.support.SupportChatService
import cbedoy.stateshopapp.support.SupportUseCase

val fragmentModule = module {
    factory {
        ProductDetailFragment()
    }
    factory {
        ProductListFragment()
    }
}

val viewModelModule = module {
    viewModel {
        ProductListViewModel(orderUseCase = get(), useCase = get(), coroutineScope = get(), supportUseCase = get())
    }
    viewModel {
        OrderViewModel(userCase = get())
    }
    viewModel {
        CheckoutViewModel(orderUseCase = get())
    }
    viewModel {
        ProductDetailViewModel(orderUseCase = get())
    }
}

val useCaseModule = module {
    single {
        OrderUseCase()
    }
    single {
        PokeProductListUseCase(repository = get())
    }
    single {
        SupportUseCase(supportChatService = get())
    }
    single {
        RickAndMortyUseCase(repository = get())
    }
}

val repositoryModule = module {
    single {
        PokeProductListRepository(service = get())
    }
    single {
        RickAndMortyRepository(service = get())
    }
}

val serviceModule = module {
    single {
        buildRetrofitInstance("https://pokeapi.co/api/").create(PokeProductListService::class.java)
    }
    single {
        buildRetrofitInstance("https://rickandmortyapi.com/api/").create(RickAndMortyService::class.java)
    }
}

val commonModule = module {
    single {
        CoroutineScope(Dispatchers.IO)
    }
    single {
        SupportChatService(context = androidContext())
    }
}

val appModule = fragmentModule + viewModelModule + useCaseModule + repositoryModule + serviceModule + commonModule