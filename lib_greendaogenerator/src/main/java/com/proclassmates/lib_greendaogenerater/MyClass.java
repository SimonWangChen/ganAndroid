package com.proclassmates.lib_greendaogenerater;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {

    public static void main(String args[]){
        Schema schema = new Schema(1, "com.proclassmates.ganandroid.db");

        Entity son = schema.addEntity("Son");
        son.addStringProperty("name");
        son.addIntProperty("age");
        son.addIdProperty();
        Property fatherId = son.addLongProperty("fatherId").getProperty();

        Entity father = schema.addEntity("Father");
        father.addStringProperty("name");
        father.addIntProperty("age");
        father.addIdProperty();

        son.addToOne(father, fatherId);


        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
