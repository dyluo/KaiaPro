package com.luo.commonbusiness.fragment

import com.luo.baselibrary.base.BaseFragment
import org.kodein.di.Kodein

/**
 *<pre>
 *  Created by jl_luo, 2019/10/18
 *  luo.junliang5811@Gmail.com
 *  desc:
 *</pre>
 */
abstract class BaseVMFragment : BaseFragment(){
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }

}