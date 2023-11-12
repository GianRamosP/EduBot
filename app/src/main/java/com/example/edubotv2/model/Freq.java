package com.example.edubotv2.model;

public class Freq {
    String pregunta, userId;

    public Freq(String pregunta, String userId) {
        this.pregunta = pregunta;
        this.userId = userId;
    }

    public Freq() {

    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
