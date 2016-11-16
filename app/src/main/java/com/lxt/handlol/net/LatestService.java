package com.lxt.handlol.net;

import com.lxt.handlol.module.latest.ZuixinBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/10/14 0014.
 * e-mail:871281347@qq.com
 */

public interface LatestService {
    //id=12&page={data}&plat=android&version=9709
    @GET("php_cgi/news/php/varcache_getnews.php?")
    Observable<ZuixinBean> getLatestinfo(@Query("id") String data,
                                         @Query("page") String page,
                                         @Query("plat") String plat,
                                         @Query("version") String version);
}
