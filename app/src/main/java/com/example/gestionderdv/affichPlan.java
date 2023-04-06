package com.example.gestionderdv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import  android.widget.Spinner;
import android.widget.CalendarView;
import android.os.Bundle;
import android.widget.TextView;

public class affichPlan extends AppCompatActivity {

    CalendarView calendarview;
    String curDate;
    Spinner proRdv;
    String[] rdvs;
    String[] pros;
    Integer i;
    Modele db;
    EditText text;
    int idSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affich_plan);
        db=new Modele(this);
        calendarview = (CalendarView) findViewById(R.id.calendarRdv);
        proRdv = (Spinner) findViewById(R.id.SpinProRdv);
        text = findViewById(R.id.editTextTextPersonName);

     //   majListe("");

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                curDate = String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1);
            }
        });
/*

*/
    }

    //Met à jour la liste du spinner pour afficher les bons pros en fonction de la date sélectionné en paramètre
    public void majListe(String date) {

        Cursor data = db.getDataRdv(date);

      text.setText("nb" + data.getCount());
      pros = new String[data.getCount()];
       i=0;
       while (data.moveToNext()) {

           pros[i] = String.valueOf(data.getString(3));
           i++;
       }

    }

    //Affiche les pro concerné par la date selectionné par le calendrier
        public void afficherRdv (View view){
            text.setText(curDate);
            majListe(curDate);
            ArrayAdapter<String> aaPro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pros);
            aaPro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            proRdv.setAdapter(aaPro);
            proRdv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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
    }

