package com.nd.tepia.entities.DTO;

import java.io.Serializable;

import com.nd.tepia.entities.User;

public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1l;
    
    private Long id;
    private String byname;
    private Integer country;

    public UserDTO(){

    }

    public UserDTO(User obj){
        this.id = obj.getId();
        this.byname = obj.getByname();
        this.country = obj.getCountry().getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getByname() {
        return byname;
    }

    public void setByname(String byname) {
        this.byname = byname;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    
}
