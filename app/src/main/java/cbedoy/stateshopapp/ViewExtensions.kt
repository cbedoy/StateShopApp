package cbedoy.stateshopapp

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.*

fun RecyclerView.addCustomGridDecoration(){
    addItemDecoration(
        object : RecyclerView.ItemDecoration() {
            val span = 15.px
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if ((parent.getChildLayoutPosition(view) + 1) % 2 == 0){
                    outRect.left = span
                }else{
                    outRect.right = span
                }
            }
        }
    )
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Number?.formatAmount(): String {
    val numberInstance = NumberFormat.getNumberInstance(Locale.US)
    numberInstance.minimumFractionDigits = 2
    numberInstance.maximumFractionDigits = 2
    return "$${numberInstance.format(this ?: 0)}"
}

fun View.setBackgroundColorFromString(color: String) {
    if (color.contains("#") && color.length == 7){
        setBackgroundColor(Color.parseColor(color))
    }else{
        setBackgroundColor(ContextCompat.getColor(context, R.color.background_color))
    }
}