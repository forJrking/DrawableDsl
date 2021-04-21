package com.github.forjrking.drawable

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.github.forjrking.drawable.DrawableBuilder.Companion.context


/**
 * @author: forjrking
 * @date: 2021/4/19 5:05 PM
 * @param resId 文件资源id
 */
class ResourceBuilder(@DrawableRes val resId: Int) : DrawableBuilder {

    override fun build(): Drawable {
        return ContextCompat.getDrawable(context, resId)!!
    }

    init {
        require(resId != 0) { "Id can not be 0" }
    }
}