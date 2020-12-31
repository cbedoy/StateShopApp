# StateShopApp (Template)

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

```sh
$ git clone git@github.com:cbedoy/StateShopApp.git StateShopApp
$ cd StateShopApp
$ ./gradlew assembleDebug
```


### How it works?
### Code


#### Fragment/Activity
It defines view controller or view

```kotlin
class ProductListFragment : StateiFragment(){
    private val viewModel by inject<ProductListViewModel>()
    override val title: String
        get() = "Products"
    ...
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding.recyclerView){
        adapter = productAdapter
        layoutManager = GridLayoutManager(context, 2)
    }...
```
#### State
It represents states such display or defines events, like ShowLoader, HideLoader, ReloadInfo etc.
```kotlin
sealed class ProductListState {
    object ShowLoader : ProductListState()
    object HideLoader : ProductListState()
    data class LoadedProducts(val productList: List<Product>) : ProductListState()
}
```
#### Intent
It represents intents or actions than can trigger states, or communicate with another layers such UsesCases
```kotlin
sealed class ProductListIntent {
    data class ChooseProductTypeIntent(val productType: ProductGenerator.ProductType): ProductListIntent()
    data class AddProduct(val product: Product): ProductListIntent()
}
```
#### Repository
Layers such contains all stuff related with Network service and local storage.
```kotlin
class ProductRepository(
    private val service: ProductService
){
    suspend fun requestProducts() = service.loadProducts()
}
```
```kotlin
interface ProductService{
    @GET("/lorem/images")
    suspend fun loadProducts(): ProductListResponse()
}
```
#### ViewModel
Layer such keep alive current states, it can emit states, and receive intents.
```kotlin
class ProductListViewModel(
    private val orderUseCase: OrderUseCase
): ViewModel() {
    private val _productListState = MutableStateFlow<ProductListState>(ProductListState.ShowLoader)
    val productListState : StateFlow<ProductListState> get() = _productListState
    fun handleIntent(intent: ProductListIntent){
        when(intent){
            is ProductListIntent.ChooseProductTypeIntent -> {
                chooseItem(productType = intent.productType)
            }
            is ProductListIntent.AddProduct -> {
                orderUseCase.addProduct(intent.product)
            }
        }...
    }
```
#### UseCase
Layer such contains all bussiness logic.
```kotlin
class OrderUseCase {
    private val currentOrder: Order = Order()

    fun getCurrentOrder() = currentOrder.apply {
        groupProducts.clear()
        groupProducts.addAll(products.groupBy { it.name }.map {
            ProductGroup(it.value)
        })
    }
```

Example using flow<State>:

- PreparingProducts: It can probably display a loader.
- Request Products, once you get product it continues
- Map one per one products and emit it
- Delay about 2000ms
- AllProductsAreDone: It can probably dismiss loader and show any fancy animation
```kotlin
class OrderUseCase {
    private val currentOrder: Order = Order()

    fun generateProductsWithDelay() = flow{
        emit(PreparingProducts())
        val products = repository.requestProducts()
        products.map{
            emit(PreparedItem(item = product.toItem))
        }
        delay(2000)
        emit(AllProductsAreDone())
    }
```



#### Develop by

Carlos Bedoy [Contact](https://github.com/cbedoy)

### Todos

 - Write MORE Tests
 - Add Night Mode

License
----

MIT


**Line by line, logic and syntax, my dreams explode**


