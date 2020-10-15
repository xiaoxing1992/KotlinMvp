package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.net.RetrofitManager
/**
 * Created by xuhao on 2017/11/29.
 * desc: 分类数据模型
 */
class CategoryModel {


    /**
     * 获取分类信息
     */
   suspend fun getCategoryData(): ArrayList<CategoryBean> {
        return RetrofitManager.service.getCategory()
    }
}