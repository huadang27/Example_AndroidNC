package com.example.example_btl_androidnc.students.model;

import java.io.Serializable;

public class Rank  implements Serializable {
    private String id;
    private float midtermGrades;

    private float finalGrades;

    private float exams;
    private float avg;
    private String ranking;

    public Rank(float midtermGrades, float finalGrades, float exams, float avg, String ranking) {
        this.midtermGrades = midtermGrades;
        this.finalGrades = finalGrades;
        this.exams = exams;
        this.avg = avg;
        this.ranking = ranking;
    }

    public Rank() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getMidtermGrades() {
        return midtermGrades;
    }

    public void setMidtermGrades(float midtermGrades) {
        this.midtermGrades = midtermGrades;
    }

    public float getFinalGrades() {
        return finalGrades;
    }

    public void setFinalGrades(float finalGrades) {
        this.finalGrades = finalGrades;
    }

    public float getExams() {
        return exams;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "midtermGrades=" + midtermGrades +
                ", finalGrades=" + finalGrades +
                ", exams=" + exams +
                ", avg=" + avg +
                ", ranking='" + ranking + '\'' +
                '}';
    }

    public void setExams(float exams) {
        this.exams = exams;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}