package com.example.kotlinproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.databinding.ItemLayoutBinding
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random
class ItemListViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

class ItemListAdapter(private var itemList: List<String>): RecyclerView.Adapter<ItemListViewHolder>() {
    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val binding = holder.binding
        val item = this.itemList[position]
        binding.haksuTxt.text = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder
            = ItemListViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

}
class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://sugang.inha.ac.kr/sugang/SU_53001/Remain_Search.aspx"
    val cookie: String? by lazy {
        var intent = Intent()
        intent.getStringExtra("cookie")
    }
    lateinit var randomTime:Random
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ItemListAdapter
    var job = Job()
    var haksu = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        haksu.add("CSE2104001")
        randomTime = Random(1)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter = ItemListAdapter(haksu)
        binding.itemRv.adapter = adapter
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val str = binding.editText.text.toString()
            haksu.add(str)
            binding.editText.text = null
            adapter.notifyDataSetChanged()
        }
        binding.start.setOnClickListener {
            binding.btn.visibility = View.GONE
            binding.editText.visibility = View.GONE
            binding.itemRv.visibility = View.GONE
            binding.start.visibility = View.GONE
            binding.end.visibility = View.VISIBLE
            binding.progressCircular.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO + job).launch {
                var flag = true
                var index = 0
                var randTime = ((1..9).random()*1000).toLong()
                while(flag) {
                    flag = temp(haksu[index])
                    index = (index+1) % haksu.size
                    delay(randTime)
                }
            }
            //Coroutine 실행
        }
        binding.end.setOnClickListener {
            job.cancel()
            binding.progressCircular.visibility = View.GONE
            binding.text.visibility = View.GONE
        }
    }

    fun temp(haksu: String): Boolean{
        val urlString = BASE_URL + "?gb=direct&gubun=1&haksu=${haksu}&objList=txtHaksu"
        print(urlString)
        val url = URL(urlString)
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.setRequestProperty("Cookie", cookie)
        try {
            println(urlString)
            urlConnection.connect()
            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                // 데이터 읽기
                val streamReader = InputStreamReader(urlConnection.inputStream)
                val buffered = BufferedReader(streamReader)

                val content = StringBuilder()
                while (true) {
                    val line = buffered.readLine() ?: break
                    content.append(line)
                }
                // 스트림과 커넥션 해제
                buffered.close()
                urlConnection.disconnect()
                var result = content.toString()
                result.replace("<td>", "@")
                var index = result.indexOf("<td>", 0)
                index = result.indexOf("<td>", index + 1)
                index = result.indexOf("<td>", index + 1)
                var data = result[index + 4].digitToIntOrNull()

                //println(data)
                if (data!! > 0) {
                    println("\n---------------여석났다!!!---------------")
                    binding.root.setBackgroundColor(Color.RED)
                    binding.text.text = "여석: $haksu"
                    binding.end.visibility = View.GONE
                    binding.progressCircular.visibility = View.GONE
                    binding.text.visibility = View.VISIBLE
                    return false
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }
}