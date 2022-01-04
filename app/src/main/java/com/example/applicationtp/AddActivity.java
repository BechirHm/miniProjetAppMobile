package com.example.applicationtp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText marque,etat,localisation,panne,matricule,img;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        marque = (EditText) findViewById(R.id.nametext);
        etat = (EditText) findViewById(R.id.nametext2);
        localisation = (EditText) findViewById(R.id.nametext3);
        panne = (EditText) findViewById(R.id.nametext4);
        matricule = (EditText) findViewById(R.id.nametext5);
        img = (EditText) findViewById(R.id.urlimg);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void insertData()
    {


        Map<String,Object> map = new HashMap<>();
        map.put("marque",marque.getText().toString());
        map.put("etat",etat.getText().toString());
        map.put("localisation",localisation.getText().toString());
        map.put("panne",panne.getText().toString());
        map.put("matricule",matricule.getText().toString());
        map.put("img",img.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("car").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data  Insered Successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddActivity.this,"Error while insert",Toast.LENGTH_SHORT).show();

                    }
                });

    }
    private void clearAll()
    {
        marque.setText("");
        etat.setText("");
        localisation.setText("");
        panne.setText("");
        matricule.setText("");
        img.setText("");

    }


}