package com.luo.kaiabase.ui.home

import com.luo.baselibrary.base.BaseFragment
import com.luo.kaiabase.R
import org.kodein.di.Kodein

/**
 *<pre>
 *  Created by jl_luo, 2019/10/17
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
class MineFragment : BaseFragment(){
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        //add import
//        import()
    }
    override val layoutId: Int = R.layout.fragment_mine




}