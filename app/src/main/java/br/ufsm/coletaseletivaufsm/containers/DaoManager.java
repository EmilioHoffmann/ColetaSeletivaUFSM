package br.ufsm.coletaseletivaufsm.containers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DaoManager {

    private static br.ufsm.coletaseletivaufsm.containers.DaoManager mInstance;
    private static br.ufsm.coletaseletivaufsm.containers.DaoSession mDaoSession;

    private DaoManager(Context context) {
        br.ufsm.coletaseletivaufsm.containers.DaoMaster.DevOpenHelper helper = new br.ufsm.coletaseletivaufsm.containers.DaoMaster.DevOpenHelper(context, "Containers_DB", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        br.ufsm.coletaseletivaufsm.containers.DaoMaster daoMaster = new br.ufsm.coletaseletivaufsm.containers.DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static br.ufsm.coletaseletivaufsm.containers.DaoManager getInstance() {
        if (mInstance == null) {
            throw new RuntimeException("call createSingleton on Main class");
        }
        return mInstance;
    }

    public static void createSingleton(Context context) {
        if (mInstance == null) {
            mInstance = new br.ufsm.coletaseletivaufsm.containers.DaoManager(context);
        }
    }

    public static br.ufsm.coletaseletivaufsm.containers.ContainersDao getContainersDao(){
        return mDaoSession.getContainersDao();
    }

}
