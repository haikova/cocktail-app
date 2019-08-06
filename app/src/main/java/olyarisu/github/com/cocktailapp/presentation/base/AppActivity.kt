package olyarisu.github.com.cocktailapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import olyarisu.github.com.cocktailapp.R

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        supportFragmentManager.beginTransaction().add(R.id.container, AppFragment()).commit()
    }
}