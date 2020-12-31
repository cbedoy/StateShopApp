package cbedoy.stateshopapp

import cbedoy.stateshopapp.model.Product
import kotlin.random.Random

object ProductGenerator {

    enum class ProductType {
        Mexican, Drink, American, Dessert, None
    }

    fun provideProductsOf(type: ProductType): List<Product> {
        return mapProducts(when(type){
            ProductType.Mexican -> mexicanContent
            ProductType.Drink -> drinkContent
            ProductType.American -> americanContent
            ProductType.Dessert -> dessertContent
            //ProductType.None -> emptyList()
            else -> emptyList()
        })
    }

    private fun mapProducts(contents: List<Content>): List<Product> {
        return contents.mapIndexed { index, content ->
            Product(
                    name = content.name,
                    amount = (Random.nextFloat() % 100) * 10,
                    color = colors[content.name.value % colors.size],
                    image = content.image,
                    description = "Lorem ipsum dolor sit amet consectetur adipiscing elit, penatibus nisi euismod ut nulla vehicula ridiculus interdum, cubilia imperdiet neque aliquet pharetra nibh."
            )
        }
    }

    private val mexicanContent = listOf(
        Content(name = "Tacos", image = "https://png.pngtree.com/png-vector/20190927/ourmid/pngtree-taco-vector-illustration-isolated-on-white-background-taco-clip-art-png-image_1745821.jpg"),
        Content(name = "Quesadilla", image = "https://assets.stickpng.com/images/58727f9ef3a71010b5e8ef05.png"),
        Content(name = "Burrito", image = "https://assets.stickpng.com/thumbs/58727fccf3a71010b5e8ef0b.png"),
        Content(name = "Volcan", image = "https://lostarascos.com.mx/wp-content/themes/tarascos/dist/images/platillos/ordenes/trc-corte-volcan-pastor.png"),
        Content(name = "Chimichanga", image = "https://img2.freepng.es/20180202/keq/kisspng-chimichanga-cheesecake-frutti-di-bosco-recipe-stra-chimichanga-transparent-background-5a749aaa86b8b2.2979014115175912105518.jpg"),
        Content(name = "Cocido", image = "https://img2.freepng.es/20180302/lxw/kisspng-cocido-pork-ribs-stew-curry-sweet-potato-ribs-stewed-potatoes-5a98fcb9244843.2814007615199756091486.jpg"),
        Content(name = "Menudo", image = "https://img2.freepng.es/20180531/hvc/kisspng-mexican-cuisine-menudo-taco-tinga-guatitas-menudo-5b0fb86b11b377.9467593715277569070725.jpg"),
        Content(name = "Tamal", image = "https://img1.freepng.es/20180614/clr/kisspng-tamale-mexican-cuisine-salsa-verde-menudo-recipe-tamal-5b2280d41d6ed3.6220655515289878601206.jpg"),
        Content(name = "Pollo frito", image = "https://img1.freepng.es/20180427/kzq/kisspng-fried-chicken-omelette-vegetarian-cuisine-thai-cui-ramen-5ae318df5edba8.9381570315248324793886.jpg"),
    )

    private val americanContent = listOf(
        Content(name = "Hamburger", image = "https://banner2.cleanpng.com/20171216/012/hamburger-burger-png-image-mac-burger-5a35e07354f8c3.3595786515134803073481.jpg"),
        Content(name = "Hot-Dog", image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1b7.png"),
        Content(name = "Sandwich", image = "https://pngimg.com/uploads/sandwich/sandwich_PNG60.png"),
        Content(name = "Pizza", image = "https://w7.pngwing.com/pngs/31/719/png-transparent-pizza-buffet-pepperoni-restaurant-dough-pizza-food-recipe-pizza-delivery.png"),
        Content(name = "Wings", image = "https://images.pngnice.com/download/2007/Chicken-Wings-PNG-Transparent-Image.png"),
        Content(name = "Wafles", image = "https://www.pngfind.com/pngs/m/288-2882075_belgian-waffle-free-png-image-waffles-images-png.png"),
        Content(name = "Dessert", image = "https://lh3.googleusercontent.com/proxy/X8504lozJt0r2mzISfCMmXZ16LHFrIotqcHHgG77RJ5hve8FaFCr1tsKNb4JHzz38rCRDSyY59DGpp_OExrKWfaysmHWIAF6Pj5AyvkxxQdMK8RXTYkluXsRNHUb5INp1Uk"),
        Content(name = "Nuggets", image = "https://thumbor.thedailymeal.com/htD-HBRYgjqk_U9vfDwTVSkwjYI=//https://www.thedailymeal.com/sites/default/files/story/2016/nuggets-shutterstock%20crop.jpg"),
    )

    private val drinkContent = listOf(
        Content("Beer", "https://i.pinimg.com/originals/38/80/ef/3880ef0538f22dfe20a8e03402944a0f.jpg"),
        Content("Root-Beer", "https://www.coca-cola.com.sg/content/dam/journey/sg/en/private/brands/heros/A-and-W_Sarsaparilla_Root_Beer_598x336.png"),
        Content("Caguama", "https://www.superama.com.mx/Content/images/products/img_large/0750106411302L.jpg"),
        Content("Vodka", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c23f.png"),
        Content("Mezcal", "https://i.pinimg.com/originals/c5/a9/c4/c5a9c4814efa6596f7c115f9cc7c064f.png"),
        Content("Soda", "https://img2.freepng.es/20190618/cgi/kisspng-fizzy-drinks-the-coca-cola-company-portable-networ-glass-png-tubidportal-com-5d08932feab324.3704250515608430559613.jpg")
    )

    private val dessertContent = listOf(
        Content("420", "https://png.pngtree.com/png-vector/20190328/ourmid/pngtree-hand-drawn-cannabis-logo-designs-png-image_878907.jpg"),
    )


    private val desserts = listOf("Cake", "Hot-Cake", "Churro")
    private val colors = listOf(
        "#f44336", "#e91e63", "#9c27b0", "#673ab7", "#3f51b5", "#2196f3", "#03a9f4", "#009688", "#4caf50", "#ff5722"
    )

    data class Content(
        val name: String,
        val image: String
    )

}