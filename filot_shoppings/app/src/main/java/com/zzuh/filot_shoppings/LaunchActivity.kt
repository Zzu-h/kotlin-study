package com.zzuh.filot_shoppings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        setLaunchScreenTimeOut()
    }
    private fun setLaunchScreenTimeOut(){
        CoroutineScope(Dispatchers.Default).launch {
            //delay(3000)
            startMainActivity()
        }
    }
    private fun startMainActivity(){

        // Login 상태인 확인
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}