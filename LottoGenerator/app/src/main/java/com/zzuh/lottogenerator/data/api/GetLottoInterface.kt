package com.zzuh.lottogenerator.data.api

import com.zzuh.lottogenerator.data.vo.LottoNumData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://dhlottery.co.kr/"
const val MAIN_METHOD = "getLottoNumber"

interface GetLottoInterface {
    @GET("common.do")
    fun getLotto(
        @Query("method") method: String,
        @Query("drwNo") drwNo: Int
    ): Call<LottoNumData>
}