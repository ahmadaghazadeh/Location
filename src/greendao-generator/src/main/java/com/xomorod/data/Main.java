package com.xomorod.data;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {
    String TAG="dbGenerator";
    public static void main(String[] args) throws Exception {

            Schema schema=new Schema(1,"com.xomorod.location.db");

            Entity roles=schema.addEntity("Location");
            roles.addIdProperty();
            roles.addStringProperty("Latitude");
            roles.addStringProperty("Longitude");
            roles.addStringProperty("Accuracy");
            roles.addStringProperty("LocationName");

            DaoGenerator dg=new DaoGenerator();
            dg.generateAll(schema,"./app/src/main/java");
    }
}
