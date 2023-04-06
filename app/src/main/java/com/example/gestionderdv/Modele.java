package com.example.gestionderdv;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class Modele extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Rdv.db";
    public static final String TABLE_PRO = "professionnel_table";
    public static final String TABLE_RDV = "rdvs_table";
    public static final String TABLE_REU = "reunion_table";
    public static final String COL_PRO_1 = "ID";
    public static final String COL_PRO_2 = "NOM";
    public static final String COL_PRO_3 = "PRENOM";
    public static final String COL_PRO_4 = "LETYPE";
    public static final String COL_PRO_5 = "ADRESSE";
    public static final String COL_PRO_6 = "MAIL";
    public static final String COL_PRO_7 = "TEL";
    public static final String COL_RDV_1 = "ID";
    public static final String COL_RDV_2 = "LADATE";
    public static final String COL_RDV_3 = "HEURE";
    public static final String COL_RDV_4 = "IDPRO";
    public static final String COL_REU_1 = "ID";
    public static final String COL_REU_2 = "DATE";
    public static final String COL_REU_3 = "HEURE";
    public static final String COL_REU_4 = "SALLE";
    public static final String COL_REU_5 = "TYPE";


    public Modele(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE table "+TABLE_PRO+"( ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, PRENOM TEXT, LETYPE TEXT, ADRESSE TEXT, MAIL TEXT, TEL TEXT)");
        db.execSQL("CREATE table "+TABLE_RDV+"( ID INTEGER PRIMARY KEY AUTOINCREMENT, LADATE TEXT, HEURE TEXT, IDPRO TEXT)");
        db.execSQL("CREATE table "+TABLE_REU+"( ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, HEURE TEXT, SALLE TEXT, TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_RDV);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REU);
        onCreate(db);
    }

    /**
     * Cette fonction permet d'inserer un pro dans la base de donnée
     *
     * @param nom le nom du prefessionnel qu'on insert
     * @param prenom le prénom du professionel qu'on insert
     * @param type le type de professionnel qu'on insert (exemple : pharmacien, généraliste, ...)
     * @param adresse l'adresse du professionnel qu'on insert
     * @param mail l'adresse mail du professionnel qu'on insert
     * @param tel le numéro de téléphone du professionel qu'on insert
     */
    public void insertDataPro( String nom, String prenom, String type, String adresse, String mail, String tel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PRO_2, nom);
        contentValues.put(COL_PRO_3, prenom);
        contentValues.put(COL_PRO_4, type);
        contentValues.put(COL_PRO_5, adresse);
        contentValues.put(COL_PRO_6, mail);
        contentValues.put(COL_PRO_7, tel);
        db.insert(TABLE_PRO,null,contentValues);
        db.close();
    }

    /**
     * Cette fonction permet d'inserer une reunion dans la base de donnée.
     * @param date la date de la reunion qu'on insert
     * @param heure l'heure de la reunion qu'on insert
     * @param salle la salle de la reunion qu'on insert
     * @param type le type de la reunion qu'on insert
     */
    public void insertDataReu( String date, String heure, String salle, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_REU_2, date);
        contentValues.put(COL_REU_3, heure);
        contentValues.put(COL_REU_4, salle);
        contentValues.put(COL_REU_5, type);
        db.insert(TABLE_REU,null,contentValues);
        db.close();
    }
    /**
     * Cette fonction permet d'inserer un rendez-vous dans la base de donnée
     *
     * @param date La date du rendez-vous qui doit etre entré comme ceci 'jour/mois'
     * @param heure L'heure du rendez-vous
     * @param idProConcern L'identifiant du professionnel concerné par le rendez-vous qui est selectionné avec un spinner
     */
    public void insertDataRdv( String date, String heure, String idProConcern){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_RDV_2, date);
        contentValues.put(COL_RDV_3, heure);
        contentValues.put(COL_RDV_4, idProConcern);
        db.insert(TABLE_RDV,null,contentValues);
        db.close();
    }
    /**
     * Cette fonction renvoie tout les professionnels de la table PRO
     * @return      Un cursor contenant chaque ligne de la table PRO
     */
    public Cursor getAllDataPro(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_PRO, null);

        return result;
    }

    /**
     * Cette fonction renvoie toutes les reunions de la table REU
     * @return  Un cursor contenant chaque ligne de la table REU
     */
    public Cursor getAllDataReu(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_REU, null);

        return result;
    }
    /**
     * Cette fonction permet de trouver un professionnel par son id
     * @param unId L'identifiant du professionel qu'on veut
     * @return  Retourne la ligne du professionnel selectionné par l'identifiant
     */
    public Cursor getDataPro(int unId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_PRO+" where ID ='"+unId+"'", null);

        return result;
    }
    /**
     * Cette fonction renvoie tout les rendez-vous de la table RDV
     * @return      Un cursor contenant chaque ligne de la table RDV
     */
    public Cursor getAllDataRdv(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_RDV, null);

        return result;
    }

    /**
     * Cette fonction renvoie toutes les reunions de la table Reu qui on pour date celle en paramètre
     * @param uneDate la date recu pour trouver la réunion
     * @return on renvoie un curseur de résultats correspondants
     */
    public Cursor getDataReu(String uneDate){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_REU+" where DATE ='"+uneDate+"'", null);
        //Cursor result2=db.rawQuery("select * from "+TABLE_PRO+" where ID ='"+result.getInt(0)+"'",null);
        return result;
    }
    /**
     * Cette fonction renvoie le/les rendez-vous correspondant à la date en paramètre
     * @param unDate La date du rendez-vous qu'on souhaite
     * @return La ligne du rendez-vous qu'on souhaite de la table RDV
     */
    public Cursor getDataRdv(String unDate){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_RDV+" where LADATE ='"+unDate+"'", null);
        //Cursor result2=db.rawQuery("select * from "+TABLE_PRO+" where ID ='"+result.getInt(0)+"'",null);
        return result;
    }
    /**
     * Cette fonction renvoie un/des pro(s) en fonction du code postal entré en paramètre
     * @param unCp Le code postal qu'a rentré l'utilisateur pour rechercher un professionel corespondant
     * @return retourne la/les ligne(s) correspondant au(x) professionnel(s) correspondant au code postral
     */
     public Cursor getDataCp(String unCp){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_PRO+" where ADRESSE ='"+unCp+"'", null);
        //Cursor result2=db.rawQuery("select * from "+TABLE_PRO+" where ID ='"+result.getInt(0)+"'",null);
        return result;
    }

}
