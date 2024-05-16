package com.nd.tepia.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nd.tepia.entities.User;
import com.nd.tepia.entities.enums.Country;



public interface UserRepository extends MongoRepository<User, Long>{
    /**
     * Metrics only
    */
    List<User> findByCountry(Country country);

    /**
     * Gets an user by his byname, to load minimal data.
     * @param byname
     * @return <pre>User</pre>
     * 
     * @see com.nd.tepia.entities.User
    */
    User findByByname(String byname);

    /**
     * Gets an user by his email, to load minimal data.
     * @param email
     * @return <pre>User</pre>
     * 
     * @see com.nd.tepia.entities.User
    */
    User findByEmail(String email);

    /**
     * A account recuperation method, it gets the predefined {@code recuperationEmail}, to call in the 'i´ve forgot my password' section.
     * @param recuperationEmail
     * @return <pre>List&lt;User&gt;</pre>
     * 
     * @see java.util.List
     * @see com.nd.tepia.entities.User
    */
    List<User> findByRecuperationEmail(String recuperationEmail);

    /**
     * A account recuperation method, it gets the predefined {@code recuperationPhone}, to call in the 'i´ve forgot my password' section, if the user not have defined an {@code recuperationEmail}.
     * @param recuperationPhone
     * @return <pre>List&lt;User&gt;</pre>
     * 
     * @see java.util.List
     * @see com.nd.tepia.entities.User
    */
    List<User> findByRecuperationPhone(String recuperationPhone);

    /**
     * Login method, based on email and password.
     * @param email
     * @param password
     * @return <pre>User</pre>
    */
    User findByEmailAndPassword(String email, String password);

    /**
     * Login method, based on byname and password.
     * @param byname
     * @param password
     * @return <pre>User</pre>
    */
    User findByBynameAndPassword(String byname, String password);

    boolean existsByByname(String byname);

    boolean existsByEmail(String email);

}
