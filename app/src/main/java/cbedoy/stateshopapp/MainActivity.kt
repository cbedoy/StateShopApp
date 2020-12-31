package cbedoy.stateshopapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cbedoy.stateshopapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    fun reloadTitle(titleText: String){
        binding.toolbar.findViewById<TextView>(R.id.toolbar_title)?.text = titleText
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}