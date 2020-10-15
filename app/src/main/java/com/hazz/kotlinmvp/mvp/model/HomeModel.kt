package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.RetrofitManager

/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class HomeModel{

    /**
     * 获取首页 Banner 数据
     */
    suspend fun requestHomeData(num:Int):HomeBean{
        return RetrofitManager.service.getFirstHomeData(num)
    }

    /**
     * 加载更多
     */
    suspend fun loadMoreData(url:String):HomeBean{
        return RetrofitManager.service.getMoreHomeData(url)
    }



}
