package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.RetrofitManager

/**
 * Created by xuhao on 2017/11/25.
 * desc:
 */
class VideoDetailModel {

    suspend fun requestRelatedData(id:Long):HomeBean.Issue{
        return RetrofitManager.service.getRelatedData(id)
    }

}