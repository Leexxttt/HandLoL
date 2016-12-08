package com.lxt.handlol.net;

import com.lxt.handlol.module.hero.AllHero;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ${reallyCommon} on 2016/11/15 0015.
 * e-mail:871281347@qq.com
 */

public interface HeroService {
    @GET("handlol/hero_list.json")
    Observable<AllHero> getAllHeroInfo();
}
