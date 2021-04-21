package com.github.forjrking.drawable

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.TextUtils
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.github.forjrking.drawable.DrawableBuilder.Companion.cxt
import com.github.forjrking.drawable.DrawableBuilder.Companion.dp2px


/**
 * 创建时间 2019/1/3
 * 描述     shape样式的构建类，所有距离值均可传dp
 */
class ShapeBuilder : DrawableBuilder {

    enum class Shape {
        /*** Shape is a rectangle, possibly with rounded corners*/
        RECTANGLE,

        /*** Shape is an ellipse*/
        OVAL,

        /*** Shape is a line*/
        LINE,

        /*** Shape is a ring.*/
        RING,
    }

    private var mRadius = 0f
    private var mRadii: FloatArray? = null
    private var mWidth = 0f
    private var mHeight = 0f
    private var mStrokeWidth = 0f
    private var mDashWidth = 0f
    private var mDashGap = 0f
    private var mShape = GradientDrawable.RECTANGLE
    private var mSolidColor = 0
    private var mStrokeColor = 0
    private var mGradientColor: IntArray? = null
    private var mAngle = 0
    private var mPadding: Rect? = null

    /**
     * 设置四个角的圆角
     *
     * @param dpRadius 圆角的半径
     */
    fun corner(dpRadius: Float): ShapeBuilder {
        mRadius = dp2px(dpRadius)
        return this
    }

    /**
     * 分别设置四个角的圆角
     *
     * @param leftTop     左上角圆角半径
     * @param rightTop    右上角圆角半径
     * @param leftBottom  左下角圆角半径
     * @param rightBottom 右下角圆角半径
     */
    fun corner(
        leftTop: Float,
        rightTop: Float,
        leftBottom: Float,
        rightBottom: Float
    ): ShapeBuilder {
        val leftTopPx = dp2px(leftTop)
        val rightTopPx = dp2px(rightTop)
        val leftBottomPx = dp2px(leftBottom)
        val rightBottomPx = dp2px(rightBottom)
        mRadii = floatArrayOf(
            leftTopPx, leftTopPx,
            rightTopPx, rightTopPx,
            rightBottomPx, rightBottomPx,
            leftBottomPx, leftBottomPx
        )
        return this
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun padding(left: Float, top: Float, right: Float, bottom: Float): ShapeBuilder {
        mPadding = Rect().apply {
            set(
                dp2px(left).toInt(),
                dp2px(top).toInt(),
                dp2px(right).toInt(),
                dp2px(bottom).toInt()
            )
        }
        return this
    }

    /**
     * 设置size
     */
    fun size(dpWidth: Float, dpHeight: Float): ShapeBuilder {
        mWidth = dp2px(dpWidth)
        mHeight = dp2px(dpHeight)
        return this
    }

    /**
     * 设置填充色
     * @param colorId 填充的颜色
     */
    fun solid(@ColorRes colorId: Int): ShapeBuilder {
        mSolidColor = ContextCompat.getColor(cxt(), colorId)
        return this
    }

    /**
     * 设置填充色
     *
     * @param colorString 颜色的string值
     */
    fun solid(colorString: String): ShapeBuilder {
        try {
            if (!TextUtils.isEmpty(colorString)) {
                mSolidColor = Color.parseColor(colorString.trim())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return this
    }

    /**
     * 设置填充色
     *
     * @param colorString 颜色的string值
     */
    fun solid(colorString: String?, @ColorRes defaultColor: Int): ShapeBuilder {
        mSolidColor = if (!TextUtils.isEmpty(colorString) && colorString!!.startsWith("#")) {
            Color.parseColor(colorString.trim())
        } else {
            ContextCompat.getColor(cxt(), defaultColor)
        }
        return this
    }

    /**
     * 设置Drawable的形状
     *
     * @param shape 形状
     */
    fun shape(shape: Shape): ShapeBuilder {
        mShape = when (shape) {
            Shape.OVAL -> GradientDrawable.OVAL
            Shape.LINE -> GradientDrawable.LINE
            Shape.RING -> GradientDrawable.RING
            else -> GradientDrawable.RECTANGLE
        }
        return this
    }

    /**
     * 设置线条的颜色和粗细
     *
     * @param colorId 线条颜色
     * @param dpWidth 线条粗细，dp
     */
    @JvmOverloads
    fun stroke(
        @ColorRes colorId: Int, dpWidth: Float,
        dpDashGap: Float = 0f, dpDashWidth: Float = 0f
    ): ShapeBuilder {
        mStrokeWidth = dp2px(dpWidth)
        mStrokeColor = ContextCompat.getColor(cxt(), colorId)
        mDashGap = dp2px(dpDashGap)
        mDashWidth = dp2px(dpDashWidth)
        return this
    }

    /**
     * 设置线条的颜色和粗细
     *
     * @param colorString 线条颜色
     * @param dpWidth     线条粗细，dp
     */
    @JvmOverloads
    fun stroke(
        colorString: String, dpWidth: Float,
        dpDashGap: Float = 0f,
        dpDashWidth: Float = 0f
    ): ShapeBuilder {
        mStrokeWidth = dp2px(dpWidth)
        try {
            if (!TextUtils.isEmpty(colorString)) {
                mStrokeColor = Color.parseColor(colorString.trim())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mDashGap = dp2px(dpDashGap)
        mDashWidth = dp2px(dpDashWidth)
        return this
    }

    /**
     * 设置线条的颜色和粗细
     * @param colorInt 线条颜色
     */
    @JvmOverloads
    fun strokeInt(
        colorInt: Int,
        dpWidth: Float,
        dpDashGap: Float = 0f,
        dpDashWidth: Float = 0f
    ): ShapeBuilder {
        mStrokeWidth = dp2px(dpWidth)
        mStrokeColor = colorInt
        mDashGap = dp2px(dpDashGap)
        mDashWidth = dp2px(dpDashWidth)
        return this
    }

    /**
     * 设置虚线 px为单位
     *
     * @param dashGap   实线的距离
     * @param dashWidth 实线的宽度
     */
    fun dash(dashGap: Float, dashWidth: Float): ShapeBuilder {
        mDashGap = dashGap
        mDashWidth = dashWidth
        return this
    }

    /**
     * 设置渐变色
     *
     * @param startColor 起始颜色
     * @param endColor   结束颜色
     * @param angle      渐变角度，必须为45的倍数
     */
    fun gradient(
        angle: Int,
        @ColorRes startColor: Int,
        @ColorRes endColor: Int,
        @ColorRes centerColor: Int? = null,
    ): ShapeBuilder {
        require(angle % 45 == 0) { "'angle' attribute to be a multiple of 45" }
        if (centerColor != null) {
            val color = IntArray(3)
            color[0] = ContextCompat.getColor(cxt(), startColor)
            color[1] = ContextCompat.getColor(cxt(), centerColor)
            color[2] = ContextCompat.getColor(cxt(), endColor)
            mGradientColor = color
        }else{
            val color = IntArray(2)
            color[0] = ContextCompat.getColor(cxt(), startColor)
            color[1] = ContextCompat.getColor(cxt(), endColor)
            mGradientColor = color
        }
        mAngle = angle
        return this
    }

    override fun build(): Drawable {
        val gradientDrawable: GradientDrawable
        if (mGradientColor != null) {
            val orientation = when (mAngle % 360) {
                45 -> GradientDrawable.Orientation.BL_TR
                90 -> GradientDrawable.Orientation.BOTTOM_TOP
                135 -> GradientDrawable.Orientation.BR_TL
                180 -> GradientDrawable.Orientation.RIGHT_LEFT
                225 -> GradientDrawable.Orientation.TR_BL
                270 -> GradientDrawable.Orientation.TOP_BOTTOM
                315 -> GradientDrawable.Orientation.TL_BR
                0 -> GradientDrawable.Orientation.LEFT_RIGHT
                else -> GradientDrawable.Orientation.LEFT_RIGHT
            }
            gradientDrawable = GradientDrawable(orientation, mGradientColor)
        } else {
            gradientDrawable = GradientDrawable()
            gradientDrawable.setColor(mSolidColor)
        }
        if (mStrokeWidth != 0f) {
            if (mDashWidth != 0f && mDashGap != 0f) {
                gradientDrawable.setStroke(
                    (mStrokeWidth + .5f).toInt(),
                    mStrokeColor, mDashWidth, mDashGap
                )
            } else {
                gradientDrawable.setStroke((mStrokeWidth + .5f).toInt(), mStrokeColor)
            }
        }
        if (mRadius != 0f) {
            gradientDrawable.cornerRadius = mRadius
        } else if (mRadii != null) {
            gradientDrawable.cornerRadii = mRadii
        }

        if (mWidth != 0f && mHeight != 0f) {
            gradientDrawable.setSize((mWidth + .5f).toInt(), (mHeight + .5f).toInt())
        }
        mPadding?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                gradientDrawable.setPadding(it.left, it.top, it.right, it.bottom)
            }
        }
        gradientDrawable.shape = mShape
        return gradientDrawable
    }

}