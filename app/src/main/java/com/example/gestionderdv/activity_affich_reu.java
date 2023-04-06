package com.example.gestionderdv;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

public class activity_affich_reu extends AppCompatActivity {

    CalendarView calendarview;
    String curDate;
    Spinner spinAllReu;
    Spinner spinReuDay;
    Spinner spinTypeReu;
    String[] allReu;
    String[] dayReu;
    String[] choixType = {"Reu Equipe", "Reu Responsable"};
    Integer i;
    Modele db;
    EditText textDateReu;
    EditText textHeureReu;
    EditText textSalleReu;
    int idSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affich_reu);
        db=new Modele(this);
        calendarview = (CalendarView) findViewById(R.id.calendarReu);
        textDateReu = (EditText) findViewById(R.id.editTextDateReu);
        textHeureReu = (EditText) findViewById(R.id.editTextHeureReu);
        textSalleReu = (EditText) findViewById(R.id.editTextSalleReu);
        spinAllReu = (Spinner) findViewById(R.id.spinnerAllReu);
        spinReuDay = (Spinner) findViewById(R.id.spinnerDayReu);
        spinTypeReu = (Spinner) findViewById(R.id.spinnerTypeReu);

        //   majListe("");

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                curDate = String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
            }
        });

        ArrayAdapter<String> aaPros = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choixType);
        aaPros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTypeReu.setAdapter(aaPros);


        spinTypeReu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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
    //Met à jour la liste du spinner pour afficher les bons pros en fonction de la date sélectionné en paramètre
    public void majDayReu(String date) {

        Cursor data = db.getDataReu(date);

        dayReu = new String[data.getCount()];
        i=0;
        while (data.moveToNext()) {

            dayReu[i] = String.valueOf("Date : "+data.getString(1)+", Heure :"+data.getString(2)+", Salle :"+data.getString(3)+", Type :"+data.getString(4));
            i++;
        }
    }
    //Met à jour la liste du spinner pour afficher les bons pros en fonction de la date sélectionné en paramètre
    public void majAllReu() {

        Cursor data = db.getAllDataReu();

        allReu = new String[data.getCount()];
        i=0;
        while (data.moveToNext()) {

            allReu[i] = String.valueOf("Date : "+data.getString(1)+", Heure :"+data.getString(2)+", Salle :"+data.getString(3)+", Type :"+data.getString(4));
            i++;
        }
    }

    public void affichDayReu (View view){
        majDayReu(curDate);
        ArrayAdapter<String> aaPro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dayReu);
        aaPro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinReuDay.setAdapter(aaPro);
    }
    public void enregistrerReu (View view){
        String unType = choixType[idSelect];
        db.insertDataReu(textDateReu.getText().toString(), textHeureReu.getText().toString(), textSalleReu.getText().toString(), unType);

    }
    public void affichAllReu (View view){
        majAllReu();
        ArrayAdapter<String> aaPro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allReu);
        aaPro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAllReu.setAdapter(aaPro);
    }
}