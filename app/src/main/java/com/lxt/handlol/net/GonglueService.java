package com.lxt.handlol.net;

import com.lxt.handlol.module.gonglue.GonglueBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/10/21 0021.
 * e-mail:871281347@qq.com
 * http://qt.qq.com/static/pages/news/phone/c10_list_1.shtml
 */

 public interface GonglueService {
    @GET("static/pages/news/phone/{date}")
    Observable<GonglueBean> getGonglueInfo(@Path("date") String date);
}
