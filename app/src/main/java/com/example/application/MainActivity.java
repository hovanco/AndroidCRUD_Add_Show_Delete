package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements VocabularyAdapter.OnItemClicked {
    AppDatabase db;
    public Button ButtonAdd;

    RecyclerView recyclerviewVocabulary;
    VocabularyAdapter vocabularyAdapter;
    public static List<Vocabulary> vocabularies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerviewVocabulary = findViewById(R.id.recycler_view_vocabulary);
        recyclerviewVocabulary.setLayoutManager(new LinearLayoutManager((this)));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();


        // Kiểm tra đã bấm được nút lệnh add và đã chuyển trang được chưa
        ButtonAdd = findViewById(R.id.btnAdd);
        ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "YOUR MESSAGE", LENGTH_SHORT).show();
                //Intent intent = new Intent(MainActivity.this,Add.class);
                //startActivity(intent);
                openAddScreen();
            }
        });
    }
    // Hàm để xuất ra màn hình những data đã adđ được
    public void getAllStudent() {
        new AsyncTask<Void, Void, List<Vocabulary>>() {
            @Override
            protected List<Vocabulary> doInBackground(Void... voids) {
                vocabularies = db.vocabularyDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vocabularyAdapter = new VocabularyAdapter(this, vocabularies);
                        vocabularyAdapter.setOnClick(MainActivity.this);
                        recyclerviewVocabulary.setAdapter(vocabularyAdapter);
                        //Toast.makeText(MainActivity.this, "size" + memories.size(), Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }
        }.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllStudent();
    }
    public void openAddScreen() {
        Intent intent = new Intent(MainActivity.this, Add.class);
        startActivity(intent);
    }
// NGANG ĐÂY LÀ ĐÃ ADD VÀ SHOW ĐƯỢC RỒI

    @Override
    public void onItemDeleteClick(int position) {
        deleteVocabulary(position);
    }

    void deleteVocabulary(final int position){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.vocabularyDao().delete(vocabularyAdapter.getVocabularies().get(position));
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                vocabularyAdapter.getVocabularies().remove(position);
                vocabularyAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

//    @Override
//    public void onItemUpdateClick(int position) {
//
//    }
}
