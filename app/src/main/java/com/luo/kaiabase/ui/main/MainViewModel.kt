package com.luo.kaiabase.ui.main


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luo.baselibrary.core.StateLiveData
import com.luo.baselibrary.ext.retrofit
import com.luo.commonbusiness.base.BaseViewModel
import com.luo.coremodel.data.repository.UserRepository
import com.luo.coremodel.vo.ArticleList
import kotlinx.coroutines.*

/**
 *<pre>
 *  Created by jl_luo, 2019/9/20
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */

class MainViewModel (private val userRepository: UserRepository): BaseViewModel(){

     val errMsg: MutableLiveData<String> = MutableLiveData()
     val articles :StateLiveData<ArticleList> = StateLiveData()

//    private val userRepository : UserRepository by instance()

     fun test(){
        launch( {
            articles.postLoading()
            val response = withContext(Dispatchers.IO){
                userRepository.testFun()
//                val a2 = userRepository.testFun()

            }

            executeResponse(response,{
                val data1 = response.data
                articles.value = data1
//                Log.e("TT",data1.toString())
                articles.postValueAndSuccess(data1)
            },{
                articles.postError()
                errMsg.value = response.errorMsg
                Log.e("TT","${errMsg.value} ,error ")
            })

        }, catchBlock = {
            Log.e("TTT","throw: $it.localizedMessage")
        },displayCommon = true )

    }

    fun test2(){

        retrofit {

//            api =  userRepository.testFun()

            onSuccess {

            }

        }
    }


}

class MainViewModelFactory(
    private val repo: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}