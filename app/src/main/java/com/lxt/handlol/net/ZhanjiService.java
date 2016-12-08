package com.lxt.handlol.net;

import com.lxt.handlol.module.huodong.MainContent;
import com.lxt.handlol.module.my.ZhanjiBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/10/28 0028.
 * e-mail:871281347@qq.com
 */

public interface ZhanjiService {
    /**
     * http://handlol.duapp.com/handlol/zhanji.json
     * @param date
     * @return
     */
    @GET("handlol/{data}")
    Observable<ZhanjiBean> getzhanjiInfo(@Path("data") String date);
}
