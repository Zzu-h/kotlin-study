package com.example.kotlinproject

import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

const val BASE_URL = "https://sugang.inha.ac.kr/sugang/SU_53001/Remain_Search.aspx"
const val key = "AIzaSyAFDmzTnQ8KChWk2TyiHT9MSwa-NOOAGNk"
const val cookie = "_ga=GA1.3.2030052348.1644363114; _gid=GA1.3.2039327185.1644363114; this.GetLangCode=; ITISSugangHome=Grade=mXv8e05CoQE=&Term=OMdMMMW0Zl0=&Kname=whnutcBdZEalC8D3igkSxg==&Dept_kname=drIw5mzH3OD3n1hdvkfNoeZYVjEPTSa5&Major_kname=drIw5mzH3ODvn8/JKmVK+g==&Manager=ccP/ybUZCSU=&Email=zHUiB7b/iZ+5QZtz9ffj7U/RWkUnG4Ou&Stno=7gaELkYTOWxkXdI2OhD8iQ==&Dept_code=JltGKE1I2Aw=&Major_code=2WdbESNCge0=&ClientAddress=ADz5uS4qFkgpDQG9y1TErA==&Ename=hGB3u4XTh7mbozSg0iQheQ==&Dept_Ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&Major_ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&pwChgPop=ccP/ybUZCSU=; ITISSugang=bigo=C8tVQu6z9Bc=&grade=Z5u/0S/Fp58=&nowno=45rwXH+AqWQ=&change_Code=kdVkxbKvJcU=&ite_yn=qp7HMQ5dvNs=&pcode=G2D6xtRFEPg=&kicho=qp7HMQ5dvNs=&date14=9XqoGdQexa5N4+PnkwWluA==&mincredit=kdVkxbKvJcU=&maxcredit=yeQGi3jUVk8=&pre_jaesu=vP8vv5LN+dI=&date_change=8QBRNSWVq1IxLii/by9J6JjQKj6HZZLi&date_jaesu=tGyGnHFhmrA=&majors=2WdbESNCge0=&major_name=qp7HMQ5dvNs=&bokhag=qp7HMQ5dvNs=&dept_code=JltGKE1I2Aw=&major_code=2WdbESNCge0="

var haksu = mutableListOf<String>()

fun main() = with(System.out.bufferedWriter()){
    haksu.add("CSE2104001")
    var randomTime = Random(haksu.size)
    randomTime.nextInt()
    var t = haksu.size
    t = (t+1).mod(haksu.size)
    while(true) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            print("hi")
        }
       /* Executors.newSingleThreadScheduledExecutor().schedule({
            print("hi")
        }, 2, TimeUnit.SECONDS)*/
    }
}

fun temp(t: Int): Boolean{
    val urlString = "https://www.naver.com/"//BASE_URL + "?gb=direct&gubun=1&haksu=${haksu[t]}&objList=txtHaksu"
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
                return false
            }
        }

    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return true
}
