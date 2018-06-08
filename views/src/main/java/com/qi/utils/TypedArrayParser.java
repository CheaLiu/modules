package com.qi.utils;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

/**
 * Creator LiuQi
 * Date 2018/4/24
 */
public class TypedArrayParser {

    public static int getDimensionPixelSize(TypedArray typedArray, int index, int defValue) {
        if (typedArray.hasValue(index)) {
            int resourceId = typedArray.getResourceId(index, -1);
            if (resourceId != -1) {
                return typedArray.getResources().getDimensionPixelSize(resourceId);
            } else
                return typedArray.getDimensionPixelSize(index, defValue);
        }
        return defValue;
    }

    /**
     * 从给定的资源中获取像素值（不管资源是dp还是sp，最终还是返回px）
     *
     * @param typedArray
     * @param index
     * @param defValue
     * @return
     */
    public static float getDimension(TypedArray typedArray, int index, float defValue) {
        if (typedArray.hasValue(index)) {
            int resourceId = typedArray.getResourceId(index, -1);
            if (resourceId != -1) {
                return typedArray.getResources().getDimension(resourceId);
            } else
                return typedArray.getDimension(index, defValue);
        }
        return defValue;
    }

    public static String getString(TypedArray typedArray, int index) {
        if (typedArray.hasValue(index)) {
            int resourceId = typedArray.getResourceId(index, -1);
            if (resourceId != -1) {
                return typedArray.getResources().getString(resourceId);
            }
            return typedArray.getString(index);
        }
        return "";
    }

    public static int getColor(TypedArray typedArray, int index, int defValue) {
        if (typedArray.hasValue(index)) {
            int resourceId = typedArray.getResourceId(index, -1);
            if (resourceId != -1) {
                return typedArray.getResources().getColor(resourceId);
            }
            return typedArray.getColor(index, defValue);
        }
        return defValue;
    }

    public static Drawable getDrawable(TypedArray typedArray, int index) {
        if (typedArray.hasValue(index)) {
            return typedArray.getDrawable(index);
        }
        return null;
    }

    public static int getInt(TypedArray typedArray, int index, int defValue) {
        if (typedArray.hasValue(index)) {
            return typedArray.getInt(index, defValue);
        }
        return defValue;
    }
}
