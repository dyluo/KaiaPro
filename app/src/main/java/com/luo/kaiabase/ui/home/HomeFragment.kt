package com.luo.kaiabase.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.luo.baselibrary.base.BaseFragment
import com.luo.kaiabase.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein

/**
 *<pre>
 *  Created by jl_luo, 2019/10/17
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class HomeFragment :BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        findNavController().navigate(R.id.action_home_fragment_to_main_fragment)
    }

    override val layoutId: Int = R.layout.fragment_home
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_nav_test.setOnClickListener(this)

    }

}