package com.center.mycode.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/5/19.
 */
public class BaseActivity extends AppCompatActivity
{
//    public static boolean isNight = false ;
    
//    public Colorful mColorful;
    
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
//        setupColorful();
    }
    
//    /**
//     * 设置各个视图与颜色属性的关联
//     */
//    private void setupColorful() {
////        ViewGroupSetter listViewSetter = new ViewGroupSetter(mNewsListView);
////        // 绑定ListView的Item View中的news_title视图，在换肤时修改它的text_color属性
////        listViewSetter.childViewTextColor(R.id.news_title, R.attr.text_color);
////        
//        // 构建Colorful对象来绑定View与属性的对象关系
//        mColorful = new Colorful.Builder(this)
////            .backgroundDrawable(R.id.root_view, R.attr.root_view_bg)
////            // 设置view的背景图片
////            .backgroundColor(R.id.change_btn, R.attr.btn_bg)
////            // 设置背景色
////            .textColor(R.id.textview, R.attr.text_color)
////            .setter(listViewSetter) // 手动设置setter
//            .create(); // 设置文本颜色
//    }
    
//    // 切换主题
//    public void changeThemeWithColorful() {
//        if (mColorful==null){
//            Log.e("mColorful","mColorful==null");
//            return;
//        }
//        if (!isNight) {
//            mColorful.setTheme(R.style.DayTheme);
//        } else {
//            mColorful.setTheme(R.style.NightTheme);
//        }
//        isNight = !isNight;
//    }
}
