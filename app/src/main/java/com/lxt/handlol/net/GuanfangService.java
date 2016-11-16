package com.lxt.handlol.net;

import com.lxt.handlol.module.guanfang.GuanFangBean;
import com.lxt.handlol.module.yule.YuleBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/10/14 0014.
 * e-mail:871281347@qq.com
 */

public interface GuanfangService {
    @GET("static/pages/news/phone/{date}")
    Observable<GuanFangBean> getGuanfanginfo(@Path("date") String date);
}
