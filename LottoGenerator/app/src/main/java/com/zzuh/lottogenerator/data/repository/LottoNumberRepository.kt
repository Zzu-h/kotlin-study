package com.zzuh.lottogenerator.data.repository

import androidx.lifecycle.MutableLiveData
import com.zzuh.lottogenerator.data.datasource.LottoNetworkDataSource
import com.zzuh.lottogenerator.data.vo.LottoNumData

class LottoNumberRepository {
    var dataSource: LottoNetworkDataSource = LottoNetworkDataSource()
    fun fetch(turnNo: Int): MutableLiveData<LottoNumData> {
        dataSource.fetch(turnNo)

        return dataSource.downloadLottoNumberResponse as MutableLiveData<LottoNumData>
    }
}