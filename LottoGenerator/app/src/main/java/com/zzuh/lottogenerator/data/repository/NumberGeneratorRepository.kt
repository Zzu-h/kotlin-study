package com.zzuh.lottogenerator.data.repository

import androidx.lifecycle.MutableLiveData
import com.zzuh.lottogenerator.data.vo.LottoNumData
import kotlin.coroutines.CoroutineContext

class NumberGeneratorRepository {
    val data = MutableLiveData<LottoNumData>()
    private val range = (1..45)

    fun generateNumber(): LottoNumData {
        var list = mutableListOf<Int>()
        while(list.size < 6){
            var tData = range.random()
            var res = list.find{it.equals(tData)}
            if(res == null) { list.add(tData) }
        }
        list.sort()
        val temp = LottoNumData(
            "success",
            list.get(0),
            list.get(1),
            list.get(2),
            list.get(3),
            list.get(4),
            list.get(5),
        )
        data.postValue(temp)
        return temp
    }
}