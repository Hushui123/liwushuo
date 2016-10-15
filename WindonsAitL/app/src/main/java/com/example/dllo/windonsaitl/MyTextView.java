package com.example.dllo.windonsaitl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 王润方 on 2016/10/15.
 */
class MyTextView extends TextView {
    /**
     * 书写自己的TextView控件步骤：
     * 1.定义类并继承TextView
     * 2.实现父类的所有有参的构造方法
     * 3. 实现isFocused()方法
     * @param context
     */
    //如果父类有有参的构造方法，子类在继承的时候都要写。无参的构造方法不用
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean isFocused() {
        return true;
    }
}

