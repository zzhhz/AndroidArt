package com.zzh.event.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/11/6.
 *
 * @date: 2017/11/6
 * @email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @description: 图片自定义文字
 */
public class ImageText extends View {
    public ImageText(Context context) {
        this(context, null);
    }

    public ImageText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ImageText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }
}
