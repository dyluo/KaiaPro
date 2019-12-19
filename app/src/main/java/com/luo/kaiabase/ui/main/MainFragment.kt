package com.luo.kaiabase.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.luo.baselibrary.adapter.SimpleViewPagerAdapter
import com.luo.baselibrary.base.BaseFragment
import com.luo.kaiabase.R
import com.luo.kaiabase.nav.KeepStateNavigator
import com.luo.kaiabase.ui.home.HomeFragment
import com.luo.kaiabase.ui.home.MineFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.Kodein

/**
 *<pre>
 *  Created by jl_luo, 2019/10/17
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class MainFragment :BaseFragment(){
    override val layoutId: Int = R.layout.fragment_main
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navigator = KeepStateNavigator(context!!,childFragmentManager,R.id.nav_host_fragment)
        navController.navigatorProvider.addNavigator(navigator)
        navController.setGraph(R.navigation.nav_graph)

        setUpNavBottom(navHostFragment)
    }

    private fun setUpNavBottom(navHostFragment: NavHostFragment) {
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottom_nav2, navController)
    }


    private fun initViewPager(){
        viewPager.adapter = SimpleViewPagerAdapter(childFragmentManager,
            listOf(HomeFragment(),MineFragment()))


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) = Unit
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) = Unit

            override fun onPageSelected(position: Int) {
                onPageSelectChanged(position)
            }
        })

        bottom_nav2.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            onBottomNavigationSelectChanged(menuItem)
            true
        }
    }

    private fun onPageSelectChanged(position: Int) {
        bottom_nav2.menu.getItem(position).isChecked = true
    }

    private fun onBottomNavigationSelectChanged(menuItem: MenuItem) {
        when (menuItem.itemId){
            R.id.home ->{
                viewPager.currentItem = 0
            }
            R.id.mine ->{
                viewPager.currentItem =1
            }
        }
    }

}