package com.xomorod.location;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.xomorod.location.db.DaoMaster;
import com.xomorod.location.db.DaoSession;
import com.xomorod.location.db.LocationDao;


/**
 * Created by 890683 on 5/25/2016.
 */
public class DaoAPP extends Application {
    static LocationDao locationDao;
public static String dbName="location";


    public static LocationDao getLocationDao() {
        return locationDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            // Create new Db -------------------------------------------------
             DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,dbName+".db",null);
             SQLiteDatabase db=helper.getWritableDatabase();
            // ----------------------------------------------------
            // Exist Db ------------------------------------------
        /*    DBHelper helper = new DBHelper(this, "rules-db.db", null);
            SQLiteDatabase db = helper.getWritableDatabase();*/
            //----------------------------------------------------
            DaoMaster daoMaster=new DaoMaster(db);
            DaoSession daoSession=daoMaster.newSession();
            locationDao=daoSession.getLocationDao();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }
}
