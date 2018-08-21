package com.example.ero.sharedpreferencesproject.models;

public class Model {
    private String key;
    private String value;

    public Model(String key, String valu) {
        this.key = key;
        this.value = valu;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
