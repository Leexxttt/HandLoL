package com.lxt.handlol.net;

import com.lxt.handlol.module.latest.ZuixinBean;
import com.lxt.handlol.module.tuji.TujiDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/10/15 0015.
 * e-mail:871281347@qq.com
 * http://qt.qq.com/php_cgi/news/php/varcache_getpics.php?id=23969&plat=android&version=9709
 */

public interface TuJIDetailService {
    @GET("php_cgi/news/php/varcache_getpics.php?")
    Observable<TujiDetailBean> getDetailInfo(@Query("id") String data,
                                             @Query("plat") String plat,
                                             @Query("version") String version);
}
