package com.luo.kaiabase.nav

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

/**
 *<pre>
 *  Created by jl_luo, 2019/10/24
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
@Navigator.Name("keep_state_fragment")
class KeepStateNavigator(val context : Context,val fragmentManager: FragmentManager,
                        val containerId:Int) : FragmentNavigator(context,fragmentManager,containerId){

    override fun navigate(
        destination:Destination,
        args:Bundle?,
        navOptions: NavOptions?,
        navigatorExtras:Navigator.Extras?
    ):NavDestination? {
        val tag = destination.id.toString()
        val transaction = fragmentManager.beginTransaction()
        var initialNavigate = false
        val currentFragment = fragmentManager.primaryNavigationFragment
        if(currentFragment != null){
            transaction.hide(currentFragment)
        }else{
            initialNavigate = true
        }
        var fragment = fragmentManager.findFragmentByTag(tag)
        if(fragment==null){
            val className = destination.className
            fragment = fragmentManager.fragmentFactory.instantiate(context.classLoader,className)
            transaction.add(containerId,fragment,tag)
        }else{
            transaction.show(fragment)
        }
        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commitNow()

        return if(initialNavigate)
            destination
        else
            null
    }
}