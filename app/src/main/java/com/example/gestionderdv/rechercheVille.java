package com.example.gestionderdv;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class rechercheVille extends AppCompatActivity {

    Spinner proConcernCpInput;
    String[] pros;
    Integer i;
    Modele db;
    EditText cpSai;
    Integer idSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_ville);

        cpSai = (EditText)findViewById(R.id.editTextCp);
        proConcernCpInput = (Spinner)findViewById(R.id.spinnerAffichProCp);
        db = new Modele(this);

        majListe(" ");


    }
    //Met à jour la liste du spinner pour afficher les bons pros en fonction du code postal sélectionné en paramètre
    public void majListe(String unCp){
        i=0;
        Cursor data = db.getDataCp(unCp);
        pros=new String[data.getCount()];
        while (data.moveToNext()){
            pros[i] = String.valueOf(data.getString(1)+" "+data.getString(2));
            i++;
        }
    }

    //Fonction du clic qui permet d'afficher les pros concerné par le code postal saisie
    public void clicAfficherProWCp(View view){
        majListe(cpSai.getText().toString());
        ArrayAdapter<String> aaPros = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pros);
        aaPros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proConcernCpInput.setAdapter(aaPros);

        proConcernCpInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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
