package com.example.kotlinproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.databinding.ItemLayoutBinding
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
    val key = "AIzaSyAFDmzTnQ8KChWk2TyiHT9MSwa-NOOAGNk"
    val cookie = "_ga=GA1.3.2030052348.1644363114; _gid=GA1.3.2039327185.1644363114; this.GetLangCode=; ITISSugangHome=Grade=mXv8e05CoQE=&Term=OMdMMMW0Zl0=&Kname=whnutcBdZEalC8D3igkSxg==&Dept_kname=drIw5mzH3OD3n1hdvkfNoeZYVjEPTSa5&Major_kname=drIw5mzH3ODvn8/JKmVK+g==&Manager=ccP/ybUZCSU=&Email=zHUiB7b/iZ+5QZtz9ffj7U/RWkUnG4Ou&Stno=7gaELkYTOWxkXdI2OhD8iQ==&Dept_code=JltGKE1I2Aw=&Major_code=2WdbESNCge0=&ClientAddress=ADz5uS4qFkgpDQG9y1TErA==&Ename=hGB3u4XTh7mbozSg0iQheQ==&Dept_Ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&Major_ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&pwChgPop=ccP/ybUZCSU=; ITISSugang=bigo=C8tVQu6z9Bc=&grade=Z5u/0S/Fp58=&nowno=45rwXH+AqWQ=&change_Code=kdVkxbKvJcU=&ite_yn=qp7HMQ5dvNs=&pcode=G2D6xtRFEPg=&kicho=qp7HMQ5dvNs=&date14=9XqoGdQexa5N4+PnkwWluA==&mincredit=kdVkxbKvJcU=&maxcredit=yeQGi3jUVk8=&pre_jaesu=vP8vv5LN+dI=&date_change=8QBRNSWVq1IxLii/by9J6JjQKj6HZZLi&date_jaesu=tGyGnHFhmrA=&majors=2WdbESNCge0=&major_name=qp7HMQ5dvNs=&bokhag=qp7HMQ5dvNs=&dept_code=JltGKE1I2Aw=&major_code=2WdbESNCge0="

    lateinit var randomTime:Random
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ItemListAdapter
    var haksu = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        haksu.add("CSE2104001")
        randomTime = Random(haksu.size)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter = ItemListAdapter(haksu)
        binding.itemRv.adapter = adapter
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val str = binding.editText.text.toString()
            haksu.add(str)
            adapter.notifyDataSetChanged()
        }
        binding.start.setOnClickListener {
            binding.btn.visibility = View.GONE
            binding.editText.visibility = View.GONE
            binding.itemRv.visibility = View.GONE
            binding.start.visibility = View.GONE
            binding.progressCircular.visibility = View.VISIBLE

            //Coroutine 실행
        }
    }

    fun temp(haksu: String): Boolean{
        val urlString = "https://www.naver.com/"//BASE_URL + "?gb=direct&gubun=1&haksu=${haksu}&objList=txtHaksu"
        print(urlString)
        val url = URL(urlString)
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        //urlConnection.setRequestProperty("Cookie", cookie)
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