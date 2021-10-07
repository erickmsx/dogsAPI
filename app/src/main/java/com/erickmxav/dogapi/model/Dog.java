package com.erickmxav.dogapi.model;

import java.util.List;
import java.util.Map;

public class Dog {

    public Dog(){

    }

    private Map<String, List<String>> message;

    public Map<String, List<String>> getMessage() {
        return message;
    }

    public void setMessage(Map<String, List<String>> message) {
        this.message = message;
    }
}
