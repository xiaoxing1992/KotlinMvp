package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.RetrofitManager

/**
 * Created by xuhao on 2017/11/30.
 * desc: 关注Model
 */
class FollowModel {

    /**
     * 获取关注信息
     */
    suspend fun requestFollowList(): HomeBean.Issue {
        return RetrofitManager.service.getFollowInfo()
    }

    /**
     * 加载更多
     */
    suspend fun loadMoreData(url:String):HomeBean.Issue{
        return RetrofitManager.service.getIssueData(url)
    }


}
