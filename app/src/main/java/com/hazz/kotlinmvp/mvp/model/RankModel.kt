package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.RetrofitManager

/**
 * Created by xuhao on 2017/11/30.
 * desc: 排行榜 Model
 */
class RankModel {

    /**
     * 获取排行榜
     */
    suspend fun requestRankList(apiUrl:String): HomeBean.Issue {
        return RetrofitManager.service.getIssueData(apiUrl)
    }

}
