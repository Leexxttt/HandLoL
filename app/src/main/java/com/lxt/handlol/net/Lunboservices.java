package com.lxt.handlol.net;

import com.lxt.handlol.module.LunboBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/10/4 0004.
 * e-mail:871281347@qq.com
 */

public interface Lunboservices {
    @GET("static/pages/news/phone/c13_list_1.shtml")
    Observable<LunboBean> getLunboInfo();
}
