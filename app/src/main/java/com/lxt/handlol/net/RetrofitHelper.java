package com.lxt.handlol.net;


import com.lxt.handlol.HandLoLAPP;
import com.lxt.handlol.utils.LogUtil;
import com.lxt.handlol.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${reallyCommon} on 2016/9/12 0012.
 * e-mail:871281347@qq.com
 * 这是一个Retrofit管理类
 */
public class RetrofitHelper {
    public static String splash_img = "http://183.203.24.22/dlied1.qq.com/qqtalk/lolApp/images/qidong/qidong-android.jpg?mkey=57ef5fd4740a3e85&f=b110&c=0&p=.jpg";
    public static String base_lunbo_url="http://qt.qq.com/";

    public static OkHttpClient mOkHttpClient;
    public static final int CACHE_TIME_LONG = 60 * 60 * 24 * 7;

    public static RetrofitHelper builder(){
        return new RetrofitHelper();
    }
    private RetrofitHelper() {
        //在Retrofit中绑定一个okhttp
        initOkhttp();
    }
    public static  Lunboservices getLunboServices(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_lunbo_url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Lunboservices.class);
    }
    public static  HuodongService getHuodongServices(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_lunbo_url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(HuodongService.class);
    }
    public static  YuleService getYuleServices(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_lunbo_url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(YuleService.class);
    }

    public static  GuanfangService getGuanfangServices(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_lunbo_url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(GuanfangService.class);
    }
    public static  LatestService getLatestServices(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_lunbo_url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(LatestService.class);
    }
    public static  TuJIDetailService gettujidetail(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_lunbo_url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(TuJIDetailService.class);
    }
    public static  GonglueService getGonglue(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_lunbo_url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(GonglueService.class);
    }

    /**
     * 对于okhttp的一个分装
     */
    private  void initOkhttp() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    //  Log.e("这是缓存的地址：",RxZhiHuApp.getContext().getPackageName()+"===="+RxZhiHuApp.getContext().getCacheDir());
                    File file = new File(HandLoLAPP.getContext().getCacheDir(), "HttpCache");
                    Cache cache = new Cache(file, 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    private Interceptor mRewriteCacheControlInterceptor = new Interceptor()
    {

        @Override
        public Response intercept(Chain chain) throws IOException
        {

            Request request = chain.request();
            if (!NetWorkUtil.isNetworkConnected())
            {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetworkConnected())
            {
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            } else
            {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME_LONG)
                        .removeHeader("Pragma").build();
            }
        }
    };

}
