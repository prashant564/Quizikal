package com.prashD.quizzio.utils;

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.TextUtils
import androidx.core.content.res.ResourcesCompat
import com.prashD.quizzio.BaseApp

object ResourceUtils {

    internal var resources: Resources
    internal val PACKAGE_NAME: String

    init {
        resources = BaseApp.context.resources
        PACKAGE_NAME = BaseApp.context.packageName
    }

    fun toColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }
//
//    fun toDimen(dimenId: Int): Float {
//        return resources.getDimension(dimenId)
//    }
//
//    fun toInt(resId: Int): Int {
//        return resources.getInteger(resId)
//    }
//
    @JvmStatic
    fun toString(stringId: Int): String {
        return resources.getString(stringId)
    }
//
//    fun toHtmlSpan(stringId: Int): Spanned {
//        return Html.fromHtml(resources.getString(stringId))
//    }

//    fun toVectorDrawable(drawableId: Int): VectorDrawableCompat? {
//        return VectorDrawableCompat.create(resources, drawableId, BaseApp().context.theme)
//    }

    fun toDrawable(drawableId: Int): Drawable? {
        return ResourcesCompat.getDrawable(resources, drawableId, BaseApp.context.theme)
    }

    fun toDrawable(iconName: String): Drawable? {
        return if (TextUtils.isEmpty(iconName)) null else toDrawable(toResId(iconName, "drawable"))

    }

    fun toResId(identifier: String, defType: String): Int {
        return if (TextUtils.isEmpty(identifier)) 0 else resources.getIdentifier(
            identifier,
            defType,
            BaseApp.context.packageName
        )

    }

//    fun toDrawableResId(identifier: String): Int {
//        return toResId(identifier, "drawable")
//    }

//    fun toString(vararg stringIds: Int): String {
//        val builder = StringBuilder()
//
//        for (i in stringIds.indices) {
//            builder.append(toString(stringIds[i]))
//        }
//        return builder.toString()
//    }
//
//    fun toStringArray(arrayId: Int): Array<String> {
//        return resources.getStringArray(arrayId)
//    }
//
//    fun toIntArray(arrayId: Int): IntArray {
//        return resources.getIntArray(arrayId)
//    }
//
//
//    fun getStyle(styleId: Int, attrs: IntArray): TypedArray {
//        return BaseApp().context.obtainStyledAttributes(styleId, attrs)
//    }
//
//    fun getPixelSizeForDimen(dimenId: Int): Int {
//        return resources.getDimensionPixelSize(dimenId)
//    }
//
//    fun toStringFormat(stringId: Int, vararg args: Any): String {
//        return resources.getString(stringId, *args)
//    }
//
//    /* toPluralString : basically format string+returns string based on quantity */
//    fun toPluralString(pluralStringId: Int, quantity: Int, vararg args: Any): String {
//        return resources.getQuantityString(pluralStringId, quantity, *args)
//    }

}