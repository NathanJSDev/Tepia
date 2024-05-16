package com.nd.tepia.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nd.tepia.entities.enums.Country;
import com.nd.tepia.entities.enums.UserStatus;
import com.nd.tepia.entities.enums.UserType;
import com.nd.tepia.entities.properties.UserProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User implements Serializable{
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant accountCreation;
    @Size(min = 5, max = 30)
    private String byname;
    @Size(min = 10, max = 144)
    private String email;
    @Size(min = 8, max = 100)
    private String password;

    private Integer country;
    
    private Instant lastLogin;

    /**
     * For convenience this property cannot be equal the email property.
    */
    @Size(min = 10, max = 144)
    private String recuperationEmail;

    /**
     * For convenience this property cannot be equal the phone property.
    */
    @Size(min = 11, max = 25)
    private String recuperationPhone;

    /**
     * The userStatus property defines the plan the user is in, if it exists
    */
    private Integer userStatus = UserStatus.FREE.getCode();

    /**
     * The accountType property defines the permission level of the user in the system.
    */
    private Integer accountType = UserType.UNDEFINED.getCode();

    /**
     * Default values: 
     * <pre>
     * public class UserProperties{ ...
     *      private Integer theme = Theme.LIGHT_BLUE.getCode();
     *      private Boolean isOnline = false;
     *      private String profileImage = null;
     *      ...
     * }
     * </pre>
     * @see com.nd.tepia.entities.properties.UserProperties
     */
    @OneToOne
    private UserProperties properties;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private List<AppFeedback> AppFeedbacks = new ArrayList<>();

    /**
     * This list contains the id of all the other users then this user follows
    */
    private List<Long> follows = new ArrayList<>();

    /**
     * This list contains the id of all the users follow this user.
    */
    private List<Long> followers = new ArrayList<>();

    /**
     * This list contains the id of all the apps this user fovorites
    */
    private List<Long> favorites = new ArrayList<>();
    
    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<App> apps = new ArrayList<>();
    
    public User(){

    }

    public User(Long id, Instant accountCreation, String byname, String email, String password, UserType accountType, Country country) {
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        setAccountType(accountType);
        setCountry(country);
    }

    public User(Long id, Instant accountCreation, String byname, String email, String password, String recuperationEmail, String recuperationPhone, UserType accountType, Country country){
        this.id = id;
        this.accountCreation = accountCreation;
        this.byname = byname;
        this.email = email;
        this.password = password;
        this.recuperationEmail = recuperationEmail;
        this.recuperationPhone = recuperationPhone;
        setAccountType(accountType);
        setCountry(country);
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

    /**
     * For convenience this property cannot be equal the recuperationEmail property.
    */
    public void setEmail(@Size(min = 10, max = 144) String email) {
        if (email.equals(recuperationEmail)) {
            throw new RuntimeException("For convenience this property(email) cannot be equal the recuperationEmail property.");
        }else{
            this.email = email;
        }
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

    /**
     * For convenience this property cannot be equal the email property.
    */
    public void setRecuperationEmail(@Size(min = 10, max = 144) String recuperationEmail) {
        if (email.equals(recuperationEmail)) {
            throw new RuntimeException("For convenience this property(recuperationEmail) cannot be equal the email property.");
        }else{
            this.recuperationEmail = recuperationEmail;
        }
    }

    public String getRecuperationPhone() {
        return recuperationPhone;
    }

    public void setRecuperationPhone(@Size(min = 11, max = 25) String recuperationPhone) {
        this.recuperationPhone = recuperationPhone;
    }

    public UserProperties getProperties() {
        return properties;
    }

    public void setProperties(UserProperties properties) {
        this.properties = properties;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserStatus getUserStatus() {
        return UserStatus.valueOf(userStatus);
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus.getCode();
    }

    public UserType getAccountType() {
        return UserType.valueOf(accountType);
    }

    public void setAccountType(UserType accountType) {
        this.accountType = accountType.getCode();
    }

    public List<App> getApps() {
        return apps;
    }

    public List<Long> getFollows() {
        return follows;
    }

    public void addFollowedUser(Long id){
        if (!follows.contains(id)) {
            follows.add(id);
        }else throw new RuntimeException("This user is already followed");
    }

    public void removeFollowedUser(Long id){
        if(follows.contains(id)){
            follows.remove(id);
        }else throw new RuntimeException("This user is not followed. You cannot remove that");
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void addFollowerUser(Long id){
        if (!followers.contains(id)) {
            followers.add(id);
        }else throw new RuntimeException("This user is already following");
    }

    public void removeFollowerUser(Long id){
        if(followers.contains(id)){
            followers.remove(id);
        }else throw new RuntimeException("This user is not following. You cannot remove that");
    }

    public List<Long> getFavorites() {
        return favorites;
    }

    public void addFavorite(Long id){
        if (!favorites.contains(id)) {
            favorites.add(id);
        }else throw new RuntimeException("This app is already favorited");
    }

    public void removeFavorite(Long id){
        if(favorites.contains(id)){
            favorites.remove(id);
        }else throw new RuntimeException("This app is not favorited. You cannot remove that");
    }

    public List<AppFeedback> getAppFeedbacks() {
        return AppFeedbacks;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((byname == null) ? 0 : byname.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (byname == null) {
            if (other.byname != null)
                return false;
        } else if (!byname.equals(other.byname))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;

        return true;
    }

    public boolean equalsById(Long otherId){
        if (id == null) {
            if (otherId != null)
                return false;
        } else if (!id.equals(otherId))
            return false;
        return true;
    }
    
}
