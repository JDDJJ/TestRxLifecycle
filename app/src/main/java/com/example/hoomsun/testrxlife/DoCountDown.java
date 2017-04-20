package com.example.hoomsun.testrxlife;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;


/**
 * Created by hoomsun on 2017/4/19.
 */

public class DoCountDown {

    public static Observable<Long> countDown() {
        return Observable.interval(1, TimeUnit.SECONDS);

    }

    static Observable<Boolean> doBiz(List<Model> list) {
        return Observable.just(list).map(new Function<List<Model>, Boolean>() {
            Boolean bool = false;

            @Override
            public Boolean apply(List<Model> models) throws Exception {
                //遍历商品列表
                for (int i = 0; i < models.size(); i++) {
                    //拿到每件商品的时间差，转化为具体的多少天多少小时多少分多少秒
                    //并保存在商品time这个属性内
                    if (models.get(i).getType() == 0) {
                        long counttime = models.get(i).getCountTime();
                        long days = counttime / (1000 * 60 * 60 * 24);
                        long hours = (counttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                        long minutes = (counttime - days * (1000 * 60 * 60 * 24)
                                - hours * (1000 * 60 * 60)) / (1000 * 60);
                        long second = (counttime - days * (1000 * 60 * 60 * 24)
                                - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
                        //并保存在商品time这个属性内
                        String finalTime = days + "天" + hours + "时" + minutes + "分" + second + "秒";
//                        Log.e("---", finaltime);
                        models.get(i).setTime(finalTime);
                        //如果时间差大于1秒钟，将每件商品的时间差减去一秒钟，
                        // 并保存在每件商品的counttime属性内
                        if (counttime > 999) {
                            models.get(i).setCountTime(counttime - 1000);
                            bool = true;
                        } else {
                            models.get(i).setTime("可加入");
                        }
                        //因为finalTime总是比counTime大一秒
                    }
                }
                return bool;
            }
        });

    }

}
