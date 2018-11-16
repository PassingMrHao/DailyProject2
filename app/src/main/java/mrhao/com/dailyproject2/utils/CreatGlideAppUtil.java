package mrhao.com.dailyproject2.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 生成GlideApp工具类
 * 郝玉龙
 * 用法：依赖：Glide4.0以上，
 *     implementation 'com.github.bumptech.glide:glide:4.3.1'
 annotationProcessor 'com.github.bumptech.glide:compiler:4.3.1'

 * 添加工具后，点击菜单里Build->Make Project,系统会自动生成GlideApp，注意添加注释：@GlideModule
 * 参考：  https://blog.csdn.net/adarcy/article/details/79253205
 */
@GlideModule
public class CreatGlideAppUtil extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setMemoryCache(new LruResourceCache(20 * 1024 * 1024));

    }
}

