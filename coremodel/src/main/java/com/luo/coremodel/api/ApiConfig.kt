package com.luo.coremodel.api

import android.text.TextUtils

/**
 * <pre>
 * Created by jl_luo, 2019/4/23
 * luo.junliang5811@Gmail.com
 * desc:
</pre> *
 */
object ApiConfig {
    // 环境配置:测试test,生产rels 开发develop, uat
    //通过配置文件在application进行动态赋值
    var env = ""

    internal// 生产环境
    //测试环境
    // uat环境
    //开发环境
    val baseURL: String
        get() = when {
            isRelease -> "https://m.nuoyifu.com:8143/AppGateway/rs/"
            isTest -> "https://www.wanandroid.com"
            isUAT -> "https://211.148.19.203:8143/AppGateway/rs/"
            else -> "http://10.21.38.123:15322/AppGateway/rs/"
        }

    internal// 生产环境35995 35975
    // 测试环境   18084
    val fuliBaseURL: String
        get() = when {
            isRelease -> "https://m.nuoyifu.com:35995"
            isTest -> "https://test.nuoyifu.com:18073"
            isUAT -> "https://uat.nuoyifu.com:35995"
            else -> "http://10.21.38.124:10181"
        }


    internal// client.p12
    val keyStoreClientPath: String
        get() = when {
            isRelease -> "release/appclient.p12"
            isUAT -> "uat/appclient.p12"
            else -> "debug/appclient.p12"
        }

    internal// client.truststore
    val keyStoreTrustPath: String
        get() = when {
            isRelease -> "release/appclient.truststore"
            isUAT -> "uat/appclient.truststore"
            else -> "debug/appclient.truststore"
        }

    internal val keyStorePwd: String
        get() = when {
            isRelease -> "ApP+NyfPaY_FT3LS7EK"
            isUAT -> "ApP+NyfPaY_FT3LS7EK"
            else -> "123456"
        }

    internal val keyStoreTrustPwd: String
        get() = when {
            isRelease -> "ApP+NyfPaY_FT3LS7EK"
            isUAT -> "ApP+NyfPaY_FT3LS7EK"
            else -> "123456"
        }

    internal val keyResPublic: String
        get() = when {
            isRelease -> "release/rsa_public.key"
            isUAT -> "uat/rsa_public.key"
            else -> "debug/rsa_public.key"
        }

    internal val keyResPrivate: String
        get() = if (isRelease) "release/rsa_private.key" else "debug/rsa_private.key"

    val desPwd: String
        get() = when {
            isRelease -> "lhofj29nhsga39iffnh09cmezvy16ths"
            isUAT -> "kribmxk7hec214vpoqt68lpg2juzh3i0"
            else -> "1fyom9fkv1pcpvd4yw73hwrmafbagt67"
        }


    val isRelease: Boolean
        get() = TextUtils.equals("rels", env)
    val isTest: Boolean
        get() = TextUtils.equals("test", env)
    val isDevelop: Boolean
        get() = TextUtils.equals("develop", env)
    private val isUAT: Boolean
        get() = TextUtils.equals("uat", env)
}
