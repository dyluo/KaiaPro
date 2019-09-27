package com.luo.kaiabase

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.luo.baselibrary.ext.onTextchange
import com.luo.commonbusiness.activity.BaseVMActivity
import com.luo.coremodel.data.repository.UserRepository
import com.luo.coremodel.vo.ArticleList
import com.luo.kaiabase.ui.test.MainViewModel
import com.luo.kaiabase.ui.test.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.generic.instance

class MainActivity : BaseVMActivity<MainViewModel>(){
    override fun getLayoutResId(): Int  = R.layout.activity_main

    private val userRepository : UserRepository by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mText.setOnClickListener {
            mViewModel.test()
        }

        mEditText.onTextchange{
            afterTextChanged {
                Log.d("T",it.toString())
            }
        }
    }

    override fun startObserve(){
        mViewModel.apply {
            articles.observe(this@MainActivity, Observer<ArticleList> {
                it.run {
                    Log.e("tt", "-------------$it")
                }
            })

            errMsg.observe(this@MainActivity, Observer {
                it?.runCatching {
                    Log.e("TT","-----------error,$it")
                }
            })
        }
    }


    override fun providerVMClass(): Class<MainViewModel>? {
        return MainViewModel::class.java
    }

    override fun providerViewModelFactory(): ViewModelProvider.Factory? {
        return MainViewModelFactory(userRepository)
    }


}


