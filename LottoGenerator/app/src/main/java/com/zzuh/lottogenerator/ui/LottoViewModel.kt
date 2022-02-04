package com.zzuh.lottogenerator.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zzuh.lottogenerator.data.repository.LottoNumberRepository
import com.zzuh.lottogenerator.data.repository.NumberGeneratorRepository
import com.zzuh.lottogenerator.data.vo.LottoNumData

class LottoViewModel(): ViewModel() {
    private val lottoRepo = LottoNumberRepository()
    private val numberGenRepo = NumberGeneratorRepository()
    private var _turnNo = 1

    var lottoNumbers: MutableLiveData<LottoNumData> = lottoRepo.fetch(_turnNo)
    var generateNumbers: MutableLiveData<LottoNumData> = MutableLiveData<LottoNumData>()

    fun fetch(turnNo: Int): Unit = lottoNumbers.postValue(lottoRepo.fetch(turnNo).value)

    fun generator(): Unit = generateNumbers.postValue(numberGenRepo.generateNumber())
}