package com.nd.tepia.resources.formatters;

public class AppFeedbackSearchFormatter {
    
    private String id;
    private boolean value;

    private String sk;

    public AppFeedbackSearchFormatter(){}

    public AppFeedbackSearchFormatter(String id, boolean value, String sk) {
        this.id = id;
        this.value = value;
        this.sk = sk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean value() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

}
