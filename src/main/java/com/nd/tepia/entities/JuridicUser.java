package com.nd.tepia.entities;

import java.time.Instant;

import com.nd.tepia.entities.enums.Country;
import com.nd.tepia.entities.enums.UserType;

import jakarta.persistence.Entity;

@Entity
public class JuridicUser extends User {
    
    private String cnpj;
    private String name;

    public JuridicUser(){
        super();
    }

    public JuridicUser(Long id, Instant accountCreation, String byname, String email, String password, String cnpj, String name, Country country) {
        super(id, accountCreation, byname, email, password, UserType.JURIDIC, country);
        this.cnpj = cnpj;
        this.name = name;
    }

    public JuridicUser(Long id, Instant accountCreation, String byname, String email, String password, String recuperationEmail, String recuperationPhone, String cnpj, String name, Country country) {
        super(id, accountCreation, byname, email, password, recuperationEmail, recuperationPhone, UserType.JURIDIC, country);
        this.cnpj = cnpj;
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        JuridicUser other = (JuridicUser) obj;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
}
