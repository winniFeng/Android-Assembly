package com.bm.demo.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.bm.demo.constant.Constant;
import com.bm.demo.http.RequestManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;

public class MyApplication extends Application {

    static MyApplication instance;
    public DataCache dataCache;
    private final static int RATE = 8; // 默认分配最大空间的几分之一

    public MyApplication() {
        instance = this;
    }

    public static synchronized MyApplication getInstance() {
        if (instance == null)
            instance = new MyApplication();
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        RequestManager.init(this);
        // 确定在LruCache中，分配缓存空间大小,默认程序分配最大空间的 1/8
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int maxSize = manager.getMemoryClass() / RATE; // 比如 64M/8,单位为M
        initImageLoader();
        dataCache = DataCache.get(new File(Environment.getExternalStorageDirectory()
                + Constant.CACHE_JSON_DATA_PATH));
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024).tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }

    public DataCache getCache() {
        return dataCache;
    }

    //设置圆形图片
    public DisplayImageOptions getOptionsCircle() {
        return new DisplayImageOptions.Builder().showImageOnLoading(0).showImageForEmptyUri(0).displayer(new RoundedBitmapDisplayer(360)).showImageOnFail(0).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
    }

}
