package cbedoy.stateshopapp

import android.graphics.Color
import android.widget.TextView
import java.lang.Exception

val String.value : Int
    get()  {
        var value = 0
        forEach {
            value += it.toInt()
        }
        return value
    }

fun TextView.setTextColorString(colorString: String){
    try {
        setTextColor(Color.parseColor(colorString))
    }catch (e: Exception){
        setTextColor(Color.parseColor("#333333"))
    }
}