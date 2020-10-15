package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.RetrofitManager

/**
 * Created by xuhao on 2017/11/30.
 * desc: 分类详情的 Model
 */
class CategoryDetailModel {

    /**
     * 获取分类下的 List 数据
     */
    suspend fun getCategoryDetailList(id: Long): HomeBean.Issue {
        return RetrofitManager.service.getCategoryDetailList(id)
    }

    /**
     * 加载更多数据
     */
    suspend fun loadMoreData(url: String): HomeBean.Issue {
        return RetrofitManager.service.getIssueData(url)
    }
}