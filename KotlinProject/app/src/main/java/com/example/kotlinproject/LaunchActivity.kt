package com.example.kotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinproject.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {
    lateinit var binding: ActivityLaunchBinding
    val cookie = "_ga=GA1.3.2030052348.1644363114; _gid=GA1.3.2039327185.1644363114; this.GetLangCode=; ITISSugangHome=Grade=mXv8e05CoQE=&Term=OMdMMMW0Zl0=&Kname=whnutcBdZEalC8D3igkSxg==&Dept_kname=drIw5mzH3OD3n1hdvkfNoeZYVjEPTSa5&Major_kname=drIw5mzH3ODvn8/JKmVK+g==&Manager=ccP/ybUZCSU=&Email=zHUiB7b/iZ+5QZtz9ffj7U/RWkUnG4Ou&Stno=7gaELkYTOWxkXdI2OhD8iQ==&Dept_code=JltGKE1I2Aw=&Major_code=2WdbESNCge0=&ClientAddress=ADz5uS4qFkgpDQG9y1TErA==&Ename=hGB3u4XTh7mbozSg0iQheQ==&Dept_Ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&Major_ename=XC9PjrV2jtMcOfESGC/kZIp5AzpLxfvv&pwChgPop=ccP/ybUZCSU=; ITISSugang=bigo=C8tVQu6z9Bc=&grade=Z5u/0S/Fp58=&nowno=45rwXH+AqWQ=&change_Code=kdVkxbKvJcU=&ite_yn=qp7HMQ5dvNs=&pcode=G2D6xtRFEPg=&kicho=qp7HMQ5dvNs=&date14=9XqoGdQexa5N4+PnkwWluA==&mincredit=kdVkxbKvJcU=&maxcredit=yeQGi3jUVk8=&pre_jaesu=vP8vv5LN+dI=&date_change=8QBRNSWVq1IxLii/by9J6JjQKj6HZZLi&date_jaesu=tGyGnHFhmrA=&majors=2WdbESNCge0=&major_name=qp7HMQ5dvNs=&bokhag=qp7HMQ5dvNs=&dept_code=JltGKE1I2Aw=&major_code=2WdbESNCge0="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        binding.btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("cookie", binding.editText.text)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}