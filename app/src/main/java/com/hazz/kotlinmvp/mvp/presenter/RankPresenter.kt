package com.hazz.kotlinmvp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contract.RankContract
import com.hazz.kotlinmvp.mvp.model.RankModel
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by xuhao on 2017/11/30.
 * desc: 获取 TabInfo Presenter
 */
class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {

    private val rankModel by lazy { RankModel() }


    /**
     *  请求排行榜数据
     */
    override fun requestRankList(apiUrl: String) {
        checkViewAttached()
        mRootView?.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val issue = rankModel.requestRankList(apiUrl)
                mRootView?.apply {
                    dismissLoading()
                    setRankList(issue.itemList)
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