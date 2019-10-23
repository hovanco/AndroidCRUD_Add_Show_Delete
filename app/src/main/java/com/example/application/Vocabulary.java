package com.example.application;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Copy
@Entity
public class Vocabulary {
    // Copy
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Khai báo thuộc tính
   private String vocabulary;
   private String mean;

    public Vocabulary(int id, String vocabulary, String mean) {
        this.id = id;
        this.vocabulary = vocabulary;
        this.mean = mean;
    }

    public Vocabulary() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
