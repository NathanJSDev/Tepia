package com.nd.tepia.resources.formatters;

public class AppFeedbackSearchFormatter {
    
    private String id;
    private boolean value;

    private String sk;

    private Long user_id;
    private Long app_id;

    public AppFeedbackSearchFormatter(){}

    public AppFeedbackSearchFormatter(String id, boolean value, String sk) {
        this.id = id;
        this.value = value;
        this.sk = sk;
    }

    public AppFeedbackSearchFormatter(String id, boolean value, String sk, Long user_id, Long app_id) {
        this.id = id;
        this.value = value;
        this.sk = sk;
        this.user_id = user_id;
        this.app_id = app_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getApp_id() {
        return app_id;
    }

    public void setApp_id(Long app_id) {
        this.app_id = app_id;
    }

    
}
