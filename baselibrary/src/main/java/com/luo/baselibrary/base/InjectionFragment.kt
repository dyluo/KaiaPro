package com.luo.baselibrary.base

import androidx.fragment.app.Fragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.kcontext

/**
 *<pre>
 *  Created by jl_luo, 2019/9/19
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
abstract class InjectionFragment :Fragment(),KodeinAware{
    protected val parentKodein by closestKodein()

    override val kodeinContext = kcontext<Fragment>(this)
}