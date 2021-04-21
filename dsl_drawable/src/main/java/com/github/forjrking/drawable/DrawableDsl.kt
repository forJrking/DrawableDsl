package com.github.forjrking.drawable

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * shape dsl构建方法
 * @date: 2021/4/20 2:45 PM
 */
inline fun shapeDrawable(builder: ShapeBuilder.() -> Unit): Drawable {
    return ShapeBuilder().also(builder).build()
}

/**
 * selector dsl构建方法
 * @date: 2021/4/20 2:45 PM
 */
inline fun selectorDrawable(builder: SelectorBuilder.() -> Unit): Drawable {
    return SelectorBuilder().also(builder).build()
}

/**资源*/
fun resourceDrawable(@DrawableRes resId: Int): Drawable {
    return ResourceBuilder(resId).build()
}

/**
 * InsetDrawable dsl构建方法
 * @date: 2021/4/20 2:45 PM
 */
inline fun insetDrawable(builder: InsetBuilder.() -> Unit): Drawable {
    return InsetBuilder().also(builder).build()
}

/**
 * layerDrawable dsl构建方法
 * @date: 2021/4/20 2:45 PM
 */
inline fun layerDrawable(builder: LayerListBuilder.() -> Unit): Drawable {
    return LayerListBuilder().also(builder).build()
}

/**
 * colorStateList dsl构建方法
 * @date: 2021/4/20 2:45 PM
 */
inline fun colorStateList(
    @ColorRes colorId: Int,
    builder: ColorStateListBuilder.() -> Unit
): ColorStateList {
    return ColorStateListBuilder(colorId).also(builder).build()
}

/**为所有ImageView添加 infix函数去掉括号*/
infix fun ImageView.src(drawable: Drawable?) {
    this.setImageDrawable(drawable)
}