package com.attodeba.ads.tp_dao.appconfig;

import android.app.Application;

import com.orm.SchemaGenerator;
import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarDb;
public class AppConfig extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
       SugarContext.terminate();
    }
}
