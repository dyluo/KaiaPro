package com.luo.kaiabase

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.luo.commonbusiness.activity.BaseVMActivity
import com.luo.coremodel.api.ApiServices
import com.luo.coremodel.data.repository.UserRepository
import com.luo.kaiabase.ui.main.MainViewModel
import com.luo.kaiabase.ui.main.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.generic.instance

class MainActivity : BaseVMActivity<MainViewModel>(){
    override fun getLayoutResId(): Int  = R.layout.activity_main

    private var currentNavController: LiveData<NavController>?=null
    private val userRepository : UserRepository by instance()
    private val apiServices : ApiServices by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            setupBottomNavigationBar()
        }


        mViewModel.test("hello world")
        mViewModel.multiTest()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(R.navigation.nav_home,R.navigation.nav_mine)


        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottom_nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }



    //
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        mText.setOnClickListener {
//            mViewModel.test()
//        }
//
//        mEditText.onTextchange{
//            afterTextChanged {
//                Log.d("T",it.toString())
//            }
//        }
//    }
//
//    override fun startObserve(){
//        mViewModel.apply {
//            articles.observe(this@MainActivity, Observer<ArticleList> {
//                it.run {
//                    Log.e("tt", "-------------$it")
//                }
//            })
//
//            errMsg.observe(this@MainActivity, Observer {
//                it?.runCatching {
//                    Log.e("TT","-----------error,$it")
//                }
//            })
//        }
//    }
//
//
    override fun providerVMClass(): Class<MainViewModel>? {
        return MainViewModel::class.java
    }

    override fun providerViewModelFactory(): ViewModelProvider.Factory? {
        return MainViewModelFactory(userRepository)
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }


}


