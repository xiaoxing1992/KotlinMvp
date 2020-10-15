package com.hazz.kotlinmvp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contract.CategoryDetailContract
import com.hazz.kotlinmvp.mvp.model.CategoryDetailModel
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by xuhao on 2017/11/30.
 * desc:
 */
class CategoryDetailPresenter:BasePresenter<CategoryDetailContract.View>(),CategoryDetailContract.Presenter{

   private val categoryDetailModel by lazy {
       CategoryDetailModel()
   }

    private var nextPageUrl:String?=null

    /**
     * 获取分类详情的列表信息
     */
    override fun getCategoryDetailList(id: Long) {
        checkViewAttached()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val issue = categoryDetailModel.getCategoryDetailList(id)
                mRootView?.apply {
                    nextPageUrl = issue.nextPageUrl
                    setCateDetailList(issue.itemList)
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    showError(e.toString())
                }
            }
        }
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                nextPageUrl?.let {
                    val issue = categoryDetailModel.loadMoreData(it)
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setCateDetailList(issue.itemList)
                    }
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    showError(e.toString())
                }
            }
        }
    }
}