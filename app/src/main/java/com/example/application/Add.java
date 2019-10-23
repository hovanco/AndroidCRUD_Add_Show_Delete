package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    AppDatabase db;
    private String vocabulary, mean;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVocabulary(); //gọi hàm ở phía dưới
                finish();
            }
        });
    }

    // Hàm để add dữ liệu user nhập vào database
    private void addVocabulary(){
        // Khai bao va tim id cua 2 dia chi user nhap text
        final EditText editVocabulary = findViewById(R.id.edit_vocabulary);
        final EditText editMean = findViewById(R.id.edit_mean);

        // nhan gia tri user nhap
        vocabulary = editVocabulary.getText().toString();
        mean = editMean.getText().toString();

        if (vocabulary.isEmpty() || mean.isEmpty()) {
            Toast.makeText(this, "Data must not null", Toast.LENGTH_SHORT).show();
            return;
        }
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Vocabulary newVocabulary = new Vocabulary();
                newVocabulary.setVocabulary(vocabulary);
                newVocabulary.setMean(mean);
                db.vocabularyDao().insert(newVocabulary);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(Add.this, vocabulary + " has been added successfully", Toast.LENGTH_SHORT).show();


            }
        }.execute();
    }

}
