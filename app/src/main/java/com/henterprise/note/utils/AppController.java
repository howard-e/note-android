package com.henterprise.note.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Howard.
 */

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // SETUP of SDKs -- ## --
        Realm.init(this);
        // SETUP of SDKs -- ## --

        RealmConfiguration config = new RealmConfiguration.Builder().
                deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * Call if the data inside a realm instance needs to be deleted
     *
     * @param config The realmConfig related to the Realm instance to be deleted
     */
    public void clearRealmDB(RealmConfiguration config) {
        try {
            Realm.deleteRealm(config); //Realm file has been deleted.
        } catch (Exception ex) {
            ex.printStackTrace();
            //No Realm file to remove.
        }
    }
}
