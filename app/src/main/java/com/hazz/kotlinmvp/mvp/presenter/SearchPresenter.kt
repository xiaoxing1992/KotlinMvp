package com.hazz.kotlinmvp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contract.SearchContract
import com.hazz.kotlinmvp.mvp.model.SearchModel
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by xuhao on 2017/12/4.
 * desc: 搜索的 Presenter
 */
class SearchPresenter : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    private var nextPageUrl: String? = null

    private val searchModel by lazy { SearchModel() }


    /**
     * 获取热门关键词
     */
    override fun requestHotWordData() {
        checkViewAttached()
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val string = searchModel.requestHotWordData()
                mRootView?.apply {
                    setHotWordData(string)
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(e),ExceptionHandle.errorCode)
                }
            }
        }
    }

    /**
     * 查询关键词
     */
    override fun querySearchData(words: String) {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val issue = searchModel.getSearchResult(words)
                mRootView?.apply {
                    dismissLoading()
                    if (issue.count > 0 && issue.itemList.size > 0) {
                        nextPageUrl = issue.nextPageUrl
                        setSearchResult(issue)
                    } else
                        setEmptyView()
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    dismissLoading()
                    //处理异常
                    showError(ExceptionHandle.handleException(e),ExceptionHandle.errorCode)
                }
            }
        }
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        checkViewAttached()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                nextPageUrl?.let {
                    val issue = searchModel.loadMoreData(it)
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setSearchResult(issue)
                    }
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(e),ExceptionHandle.errorCode)
                }
            }
        }
    }


}