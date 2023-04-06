package com.example.gestionderdv;

import android.content.Context;
import android.database.Cursor;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    //test si la fonction insertDataPro insère bien un pro
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
      //  assertEquals("com.example.gestionderdv", appContext.getPackageName());


        Modele db = new Modele(appContext);
        int nb = 0;
        Cursor c1 = db.getAllDataPro();
        nb = c1.getCount();
        db.insertDataPro("a", "a", "a", "a", "a", "a");
        Cursor c2 = db.getAllDataPro();
        int nb2 = c2.getCount();
        assertEquals(nb+1, nb2);



    }
    //test si la fonction insertDataRdv insère bien un rendez-vous
    @Test
    public void useAppContext3() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //  assertEquals("com.example.gestionderdv", appContext.getPackageName());


        Modele db = new Modele(appContext);
        int nb = 0;
        Cursor c1 = db.getAllDataRdv();
        nb = c1.getCount();
        db.insertDataRdv( "date", "heure", "idProConcern");
        Cursor c2 = db.getAllDataRdv();
        int nb2 = c2.getCount();
        assertEquals(nb+1, nb2);



    }
    //test si la fonction getDataRdv renvoie bien un rendez-vous
    @Test
    public void useAppContext2() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //  assertEquals("com.example.gestionderdv", appContext.getPackageName());


        Modele db = new Modele(appContext);
        int nb = 0;
        Cursor c1 = db.getDataRdv("12/12");
        nb = c1.getCount();

        assertEquals(2, nb);



    }
    //test si la fonction getDataReu renvoie bien une reunion
    @Test
    public void useAppContext4() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //  assertEquals("com.example.gestionderdv", appContext.getPackageName());


        Modele db = new Modele(appContext);
        int nb = 0;
        Cursor c1 = db.getDataReu("12/10/2021");
        nb = c1.getCount();

        assertEquals(1, nb);



    }


}