package com.zzuh.lottogenerator.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zzuh.lottogenerator.data.api.BASE_URL
import com.zzuh.lottogenerator.data.api.GetLottoInterface
import com.zzuh.lottogenerator.data.api.MAIN_METHOD
import com.zzuh.lottogenerator.data.vo.LottoNumData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class LottoNetworkDataSource {
    private val _downloadLottoNumberResponse = MutableLiveData<LottoNumData>()

    val downloadLottoNumberResponse: LiveData<LottoNumData> get() = _downloadLottoNumberResponse
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(GetLottoInterface::class.java)

    fun fetch(turnNo: Int){
        val callGetNumber = api.getLotto(MAIN_METHOD, turnNo)
        callGetNumber.enqueue(object :Callback<LottoNumData>{
            override fun onFailure(call: Call<LottoNumData>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<LottoNumData>, response: Response<LottoNumData>) {
                Log.d("NetworkDataSource", "$turnNo: ${response.body().toString()}")
                _downloadLottoNumberResponse.postValue(response.body() as LottoNumData)
            }
        })
    }
}