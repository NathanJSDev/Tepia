package com.nd.tepia.entities;

import java.time.Instant;
import java.time.LocalDate;

import com.nd.tepia.entities.enums.Country;
import com.nd.tepia.entities.enums.UserType;

import jakarta.persistence.Entity;

@Entity
public class CivilUser extends User{
    
    private String name;
    private String cpf;
    private LocalDate birthday;

    public CivilUser(){
        super();
    }

    public CivilUser(Long id, Instant accountCreation, String byname, String email, String password,
            UserType accountType, Country country, String name, String cpf, LocalDate birthday) {
        super(id, accountCreation, byname, email, password, accountType, country);
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
    }


    public CivilUser(Long id, Instant accountCreation, String byname, String email, String password,
            String recuperationEmail, String recuperationPhone, UserType accountType, Country country, String name,
            String cpf, LocalDate birthday) {
        super(id, accountCreation, byname, email, password, recuperationEmail, recuperationPhone, accountType, country);
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        CivilUser other = (CivilUser) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

}
