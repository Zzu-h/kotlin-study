package com.example.kotlinproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.databinding.ItemLayoutBinding
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random
import org.jsoup.select.Elements



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
    /*val cookie: String? by lazy {
        var intent = Intent()
        intent.getStringExtra("cookie")
    }*/
    val cookie: String =
        "_ga=GA1.3.889780670.1644973028; _gid=GA1.3.1558607823.1644973028; this.GetLangCode=; ITISSugangHome=Grade=mXv8e05CoQE=&Term=OMdMMMW0Zl0=&Kname=whnutcBdZEalC8D3igkSxg==&Dept_kname=drIw5mzH3OD3n1hdvkfNoeZYVjEPTSa5&Major_kname=drIw5mzH3ODvn8/JKmVK+g==&Manager=ccP/ybUZCSU=&Email=zHUiB7b/iZ+5QZtz9ffj7U/RWkUnG4Ou&Stno=7gaELkYTOWxkXdI2OhD8iQ==&Dept_code=JltGKE1I2Aw=&Major_code=2WdbESNCge0=&ClientAddress=ADz5uS4qFkgpDQG9y1TErA==&Ename=hGB3u4XTh7mbozSg0iQheQ==&Dept_Ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&Major_ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&pwChgPop=ccP/ybUZCSU=; ITISSugang=bigo=WZSaUOZVYOk=&grade=Z5u/0S/Fp58=&nowno=mXv8e05CoQE=&change_Code=kdVkxbKvJcU=&ite_yn=qp7HMQ5dvNs=&pcode=G2D6xtRFEPg=&kicho=qp7HMQ5dvNs=&date14=9XqoGdQexa5N4+PnkwWluA==&mincredit=kdVkxbKvJcU=&maxcredit=yeQGi3jUVk8=&pre_jaesu=vP8vv5LN+dI=&date_change=8QBRNSWVq1IxLii/by9J6JjQKj6HZZLi&date_jaesu=tGyGnHFhmrA=&majors=2WdbESNCge0=&major_name=qp7HMQ5dvNs=&bokhag=qp7HMQ5dvNs=&dept_code=JltGKE1I2Aw=&major_code=2WdbESNCge0="
    lateinit var randomTime:Random
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ItemListAdapter
    var job = Job()
    var mainJob = Job()
    var haksu = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        haksu.add("CSE2107")
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
                var randTime = ((2..9).random()*1000).toLong()
                while(flag) {
                    flag = temp(haksu[index])
                    index = (index+1) % haksu.size
                    delay(randTime)
                }
            }
            CoroutineScope(Dispatchers.Main + mainJob).launch {

            }
            //Coroutine 실행
        }
        binding.end.setOnClickListener {
            job.cancel()
            binding.progressCircular.visibility = View.GONE
            binding.text.visibility = View.GONE
            Toast.makeText(this, "finish",Toast.LENGTH_LONG)
        }
    }
    suspend fun uiUpdate(printHaksu: String, className: String){
        binding.root.setBackgroundColor(Color.RED)
        binding.text.text = "여석: $printHaksu \n $className"
        binding.end.visibility = View.GONE
        binding.progressCircular.visibility = View.GONE
        binding.text.visibility = View.VISIBLE
    }

    suspend fun temp(haksu: String): Boolean{
        Log.d("Tester", "${(1..100).random()}")
        val urlString = BASE_URL + "?gb=direct&gubun=1&haksu=${haksu}&objList=txtHaksu"
        val url = URL(urlString)
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.setRequestProperty("Cookie", cookie)
        var document: Document
        document = Jsoup.connect(urlString)
            .cookie("Cookie", cookie)
            .get()
        val rowList = document.select("table").get(0).select("tr")

        for(row: Element in rowList) {
            // tr 내에 있는 td 들을 select
            var cellList = row.select("td");
            if(cellList.size == 0) continue
            var data = cellList.get(7).text().toInt()
            var printHaksu = "${cellList.get(0).text()}-${cellList.get(1).text()}"
            var className = cellList.get(3).text()
            if (data!! > 0) {
                println("\n---------------여석났다!!!---------------")
                uiUpdate(printHaksu, className)
                return false
            }
        }
        return true
    }
}