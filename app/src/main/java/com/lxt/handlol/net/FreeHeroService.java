package com.lxt.handlol.net;

import com.lxt.handlol.module.filter.WeekFree;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/11/17 0017.
 * e-mail:871281347@qq.com
 */

public interface FreeHeroService {
    @GET("handlol/freeweek.json")
    Observable<WeekFree> getWeekFree();
}
