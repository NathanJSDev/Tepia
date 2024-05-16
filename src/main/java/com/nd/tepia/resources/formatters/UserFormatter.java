package com.nd.tepia.resources.formatters;

import java.time.Instant;
import java.time.LocalDate;

import com.nd.tepia.entities.User;
import com.nd.tepia.entities.enums.Country;
import com.nd.tepia.entities.enums.UserType;

public class UserFormatter extends User{

    private Long id;
    private Instant accountCreation;
    private String byname;
    private String email;
    private String password;
    private Integer country;
    private String recuperationEmail;
    private String recuperationPhone;
    private Integer accountType = UserType.UNDEFINED.getCode();

    private String name;
    private String cpf;
    private String cnpj;
    private LocalDate birthday;
    
    public UserFormatter(Long id, Instant accountCreation, String byname, String email, String password,
            Country country, UserType accountType) {
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        setCountry(country);
        setAccountType(accountType);
    }

    public UserFormatter(Long id, Instant accountCreation, String byname, String email, String password,
            Country country, String recuperationEmail, String recuperationPhone, UserType accountType) {
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        setCountry(country);
        this.recuperationEmail = recuperationEmail;
        this.recuperationPhone = recuperationPhone;
        setAccountType(accountType);
    }

    public UserFormatter(Long id, Instant accountCreation, String byname, String email, String password,
            Country country, UserType accountType, String name, String cpf, LocalDate birthday) {
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        setCountry(country);
        setAccountType(accountType);
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
    }

    public UserFormatter(Long id, Instant accountCreation, String byname, String email, String password,
            Country country, String recuperationEmail, String recuperationPhone, UserType accountType, String name,
            String cpf, LocalDate birthday) {
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        setCountry(country);
        this.recuperationEmail = recuperationEmail;
        this.recuperationPhone = recuperationPhone;
        setAccountType(accountType);
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
    }

    public UserFormatter(Long id, Instant accountCreation, String byname, String email, String password,
            Country country, UserType accountType, String name, String cnpj) {
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        setCountry(country);
        setAccountType(accountType);
        this.name = name;
        this.cnpj = cnpj;
    }

    public UserFormatter(Long id, Instant accountCreation, String byname, String email, String password,
            Country country, String recuperationEmail, String recuperationPhone, UserType accountType, String name,
            String cnpj) {
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        setCountry(country);
        this.recuperationEmail = recuperationEmail;
        this.recuperationPhone = recuperationPhone;
        setAccountType(accountType);
        this.name = name;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getAccountCreation() {
        return accountCreation;
    }

    public void setAccountCreation(Instant accountCreation) {
        this.accountCreation = accountCreation;
    }

    public String getByname() {
        return byname;
    }

    public void setByname(String byname) {
        this.byname = byname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getCountry() {
        return Country.valueOf(country);
    }

    public void setCountry(Country country) {
        this.country = country.getCode();
    }

    public String getRecuperationEmail() {
        return recuperationEmail;
    }

    public void setRecuperationEmail(String recuperationEmail) {
        this.recuperationEmail = recuperationEmail;
    }

    public String getRecuperationPhone() {
        return recuperationPhone;
    }

    public void setRecuperationPhone(String recuperationPhone) {
        this.recuperationPhone = recuperationPhone;
    }

    public UserType getAccountType() {
        return UserType.valueOf(accountType);
    }

    public void setAccountType(UserType accountType) {
        this.accountType = accountType.getCode();
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    
}
