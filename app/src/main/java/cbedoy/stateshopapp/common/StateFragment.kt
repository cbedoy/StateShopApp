package cbedoy.stateshopapp.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cbedoy.stateshopapp.MainActivity

abstract class StateFragment : Fragment() {
    abstract val title: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is MainActivity){
            (activity as MainActivity)?.reloadTitle(title)
        }
    }
}