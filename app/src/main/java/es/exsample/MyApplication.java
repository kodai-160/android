package es.exsample;

import android.app.Application;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmMigration migration = new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                RealmSchema schema = realm.getSchema();

                if (oldVersion < 2) {
                    RealmObjectSchema kakeiboSchema = schema.get("Kakeibo");
                    if (kakeiboSchema != null) {
                        if (kakeiboSchema.hasField("radioButonId")) {
                            kakeiboSchema.removeField("radioButonId");
                        }
                        if (kakeiboSchema.hasField("amout")) {
                            kakeiboSchema.removeField("amout");
                        }
                        if (!kakeiboSchema.hasField("amount")) {
                            kakeiboSchema.addField("amount", int.class);
                        }
                    }
                    oldVersion++;
                }

                // マイグレーションロジックをここに追加して、バージョン番号を適切に更新
            }
        };

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("default.realm")
                .schemaVersion(10) // 新しいバージョン番号に更新
                .migration(migration)
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
