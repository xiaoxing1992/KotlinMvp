package com.hazz.kotlinmvp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contract.CategoryContract
import com.hazz.kotlinmvp.mvp.model.CategoryModel
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by xuhao on 2017/11/29.
 * desc:分类的 Presenter
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    /**
     * 获取分类
     */
    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val categoryList = categoryModel.getCategoryData()
                mRootView?.apply {
                    dismissLoading()
                    showCategory(categoryList)
                }
            } catch (e: Exception) {
                mRootView?.apply {
                    //处理异常
                    showError(ExceptionHandle.handleException(e), ExceptionHandle.errorCode)
                }
            }
        }
    }
}