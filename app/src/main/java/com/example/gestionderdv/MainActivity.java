package com.example.gestionderdv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Modele db;
    EditText nomInput;
    EditText prenomInput;
    EditText typeInput;
    EditText adresseInput;
    EditText mailInput;
    EditText telInput;
    EditText dateInput;
    EditText heureInput;
    Spinner proConcernInput;
    String[] pros;
    Integer idSelect,i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Modele(this);

        nomInput=(EditText) findViewById(R.id.editTextNom);
        prenomInput=(EditText) findViewById(R.id.editTextPrenom);
        typeInput=(EditText) findViewById(R.id.editTextType);
        adresseInput=(EditText) findViewById(R.id.editTextAdresse);
        mailInput=(EditText) findViewById(R.id.editTextMail);
        telInput=(EditText) findViewById(R.id.editTextTel);
        dateInput=(EditText) findViewById(R.id.editTextDate);
        heureInput=(EditText) findViewById(R.id.editTextHeure);
        proConcernInput=(Spinner) findViewById(R.id.spinnerProConcern);

        majListe();

        ArrayAdapter<String> aaPros = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pros);
        aaPros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proConcernInput.setAdapter(aaPros);


        proConcernInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                        idSelect=position;
            }
            @Override
                    public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }

    public void majListe(){
        i=0;
            Cursor data = db.getAllDataPro();
            pros=new String[data.getCount()];
                while (data.moveToNext()){
                    pros[i] = String.valueOf(data.getInt(0)+"="+data.getString(1));
                    i++;
                }


    }
    //Fonction du clic Enregistrer pour un professionel
    public void insertionClicPro(View view){
        db.insertDataPro(nomInput.getText().toString(),prenomInput.getText().toString(),typeInput.getText().toString(),adresseInput.getText().toString(),mailInput.getText().toString(),telInput.getText().toString());
        majListe();
    }
    //Fonction du clic Enregistrer pour un rendez-vous
    public void insertionClicRdv(View view){
        String pro=pros[idSelect];
        String[] proSepares=pro.split("=");
        db.insertDataRdv(dateInput.getText().toString(),heureInput.getText().toString(),proSepares[0]);
    }
    //Fonction du clic Afficher permettant de changer de page vers affichPlan.java
    public void clicAfficher(View view){
        Intent intentAfficher = new Intent(this, affichPlan.class);
        startActivity(intentAfficher);
    }
    //Fonction du clic Rechercher permettant de changer de page vers rechercheVille.java
    public void clicRechercher(View view){
        Intent intentChercher = new Intent(this, rechercheVille.class);
        startActivity(intentChercher);
    }
    public void gererReu (View view){
        Intent intentGerer = new Intent(this, activity_affich_reu.class);
        startActivity(intentGerer);
    }

}