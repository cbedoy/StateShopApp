package cbedoy.stateshopapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name: String = "",
    val amount: Float = 0.0f,
    val description: String = "",
    val secondaryDescription: String = "",
    val image: String = "",
    val color: String = "",
    val type: String = ""
): Parcelable{
    val thumbnail: String
        get() {
            return if (image.isEmpty()){
                "https://dummyimage.com/300x300/${color.replace("#", "")}/FFF.png&text=${name}"
            }else image
        }
}