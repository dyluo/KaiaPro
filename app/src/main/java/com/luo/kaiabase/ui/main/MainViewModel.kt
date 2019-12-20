package com.luo.kaiabase.ui.main


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.luo.baselibrary.base.ApiResponse
import com.luo.baselibrary.core.StateLiveData
import com.luo.baselibrary.ext.retrofit
import com.luo.commonbusiness.base.BaseViewModel
import com.luo.coremodel.data.repository.UserRepository
import com.luo.coremodel.vo.Article
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

    fun multiTest1(){
        executeRequest({
            val list =mutableListOf<Article>()

            withContext(Dispatchers.IO) {
                val l = userRepository.testFun().data.datas
                Log.d("tt","${l.size}--------2--"+Thread.currentThread().name)
                list.addAll(l)
//                mutableListOf<ArticleList>().apply {
//                    add(userRepository.testFun().data)
//                    add(userRepository.testFun().data)
//                }
            }
            withContext(Dispatchers.IO) {
                val ll = userRepository.testFun().data.datas

                Log.d("tt","${ll.size}--------3--"+Thread.currentThread().name)
                list.addAll(ll)
//                mutableListOf<ArticleList>().apply {
//                    add(userRepository.testFun().data)
//                    add(userRepository.testFun().data)
//                }
            }
            list
        },onSuccess = {
            Log.e("TT",">>>>"+it.size +" >> ${Thread.currentThread().name}")
        })
    }
    fun multiTest(){
        Log.d("tt",Thread.currentThread().name)
        executeRequest({
            withContext(Dispatchers.IO) {
                Log.d("tt","--------2--"+Thread.currentThread().name)
                val oneDeferred = async {
                    userRepository.testFun()
                }
                val twoDeferred = async {
                    userRepository.testFun()
                }
                val oneResult = oneDeferred.await()
                val twoResult = twoDeferred.await()
                mutableListOf<ArticleList>().apply {
                    add(oneResult.data)
                    add(twoResult.data)
                }
            }
        },onSuccess = {
            Log.d("tt","--------3--"+Thread.currentThread().name)
            Log.e("TT",it.toString())
        })
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val oneDeferred = async {
//                    userRepository.testFun()
//                }
//                val twoDeferred = async {
//                    userRepository.testFun()
//                }
//                val oneResult = oneDeferred.await()
//                val twoResult = twoDeferred.await()
//            }
//        }
    }

     fun test(param:String){
         Log.e("TT",param)
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
        },finallyBlock = null)

    }

    fun test2(){

        retrofit {

//            api =  userRepository.testFun()

            onSuccess {

            }

        }
    }


}

class MainViewModelFactory(private val repo: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}