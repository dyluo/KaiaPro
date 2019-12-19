package com.luo.baselibrary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 *<pre>
 *  Created by jl_luo, 2019/10/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class SimpleViewPagerAdapter (fragmentManager: FragmentManager,
                              private val fragments:List<Fragment>) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }


}