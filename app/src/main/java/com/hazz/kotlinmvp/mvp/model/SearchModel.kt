package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.RetrofitManager

/**
 * Created by xuhao on 2017/11/30.
 * desc: 搜索 Model
 */
class SearchModel {

    /**
     * 请求热门关键词的数据
     */
    suspend fun requestHotWordData(): ArrayList<String> {
        return RetrofitManager.service.getHotWord()
    }


    /**
     * 搜索关键词返回的结果
     */
    suspend fun getSearchResult(words: String):HomeBean.Issue{
        return RetrofitManager.service.getSearchData(words)
    }

    /**
     * 加载更多数据
     */
    suspend fun loadMoreData(url: String): HomeBean.Issue{
        return RetrofitManager.service.getIssueData(url)
    }

}
