package ru.avito.avitotest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.avito.avitotest.view.ui.GridFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, GridFragment.newInstance())
                    .commit()
        }
    }
}