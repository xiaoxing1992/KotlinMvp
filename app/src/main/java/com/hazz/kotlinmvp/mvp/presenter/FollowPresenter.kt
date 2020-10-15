package com.hazz.kotlinmvp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contract.FollowContract
import com.hazz.kotlinmvp.mvp.model.FollowModel
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by xuhao on 2017/11/30.
 * desc: 获取 TabInfo Presenter
 */
class FollowPresenter : BasePresenter<FollowContract.View>(), FollowContract.Presenter {

    private val followModel by lazy { FollowModel() }

    private var nextPageUrl: String? = null

    /**
     *  请求关注数据
     */
    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val issue = followModel.requestFollowList()
                mRootView?.apply {
                    dismissLoading()
                    nextPageUrl = issue.nextPageUrl
                    setFollowInfo(issue)
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(e), ExceptionHandle.errorCode)
                }
            }
        }
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                nextPageUrl?.let {
                    val issue = followModel.loadMoreData(it)
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    showError(ExceptionHandle.handleException(e), ExceptionHandle.errorCode)
                }
            }
        }
    }
}