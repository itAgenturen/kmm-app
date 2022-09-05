package com.example.myfirstkmmapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstkmmapp.Greeting
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val scope = MainScope()

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = getString(R.string.loading)

        scope.launch {
            kotlin.runCatching {
                Greeting().greeting()
            }.onSuccess {
                tv.text = it
            }.onFailure {
                tv.text = it.localizedMessage
            }
        }
    }
}
