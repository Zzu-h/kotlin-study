package com.zzuh.lottogenerator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.zzuh.lottogenerator.R
import com.zzuh.lottogenerator.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    lateinit var lottoViewModel: LottoViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        lottoViewModel = LottoViewModel()

        lottoViewModel.lottoNumbers.observe(this, Observer { fetchUI() })
        lottoViewModel.fetch(1000)
        lottoViewModel.generateNumbers.observe(this, Observer { genUiUpdate() } )
        binding.clickBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch{
                lottoViewModel.generator()
            }
        }

        setContentView(binding.root)
    }
    fun genUiUpdate(){
        var value = lottoViewModel.generateNumbers.value
        if(value?.returnValue == "success"){
            cicleUpdate(binding.genNo1Item, value.drwtNo1)
            cicleUpdate(binding.genNo2Item, value.drwtNo2)
            cicleUpdate(binding.genNo3Item, value.drwtNo3)
            cicleUpdate(binding.genNo4Item, value.drwtNo4)
            cicleUpdate(binding.genNo5Item, value.drwtNo5)
            cicleUpdate(binding.genNo6Item, value.drwtNo6)
        }
    }
    fun fetchUI(){
        var value = lottoViewModel.lottoNumbers.value
        if(value?.returnValue == "success"){
            cicleUpdate(binding.drwtNo1Item, value.drwtNo1)
            cicleUpdate(binding.drwtNo2Item, value.drwtNo2)
            cicleUpdate(binding.drwtNo3Item, value.drwtNo3)
            cicleUpdate(binding.drwtNo4Item, value.drwtNo4)
            cicleUpdate(binding.drwtNo5Item, value.drwtNo5)
            cicleUpdate(binding.drwtNo6Item, value.drwtNo6)
            value.bnusNo?.let { cicleUpdate(binding.bnusNoItem, it) }
        }
    }
    private fun cicleUpdate(textView: TextView, value: Int):Unit {
        textView.text = value.toString()
        textView.setBackgroundResource(getBackColorResource(value))
    }
    private fun getBackColorResource(number: Int): Int
    = if (number <= 10) R.drawable.number_ball_yellow
    else if(number <= 20) R.drawable.number_ball_blue
    else if(number <= 30) R.drawable.number_ball_red
    else if(number <= 40) R.drawable.number_ball_grey
    else if(number <= 50) R.drawable.number_ball_green
    else R.drawable.number_ball
}
// retrofit2 에서 coroutine을 수행
// 랜덤 넘버 생성과 비교를 coroutine을 통해 수행