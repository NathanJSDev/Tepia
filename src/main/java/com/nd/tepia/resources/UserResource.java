package com.nd.tepia.resources;

import java.util.List;
import java.util.Properties;

import org.springframework.web.bind.annotation.RestController;

import com.nd.tepia.entities.CivilUser;
import com.nd.tepia.entities.JuridicUser;
import com.nd.tepia.entities.User;
import com.nd.tepia.entities.enums.UserType;
import com.nd.tepia.resources.formatters.UserFormatter;
import com.nd.tepia.entities.enums.Country;
import com.nd.tepia.services.UserService;
import com.nd.tepia.services.exceptions.BynameTryDuplicationException;
import com.nd.tepia.services.exceptions.EmailTryDuplicationException;
import com.nd.tepia.services.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
    

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/byname/{byname}")
    public ResponseEntity<User> findByByname(@PathVariable String byname) {
        return ResponseEntity.ok().body(service.findByByname(byname));
    }

    @PutMapping("sigin/{userType}")
    public ResponseEntity<User> sigin(@PathVariable String userType, @RequestBody UserFormatter entity) {
        if(!entity.equals(null)){
            if(service.existsByByname(entity.getByname())){
                throw new BynameTryDuplicationException(entity.getByname());
            }
            if(service.existsByEmail(entity.getEmail())){
                throw new EmailTryDuplicationException(entity.getEmail());
            }

            Properties pUNAME =  Validator.valide(Validator.CompiledPatterns.USER_NAME.getValue(), entity.getByname());
            Properties pUEMAIL =  Validator.valide(Validator.CompiledPatterns.EMAIL_V1.getValue(), entity.getEmail());
            Properties pUPASSWORD =  Validator.valide(Validator.CompiledPatterns.PASSWORD.getValue(), entity.getPassword());
            
            if(pUNAME.containsKey("error")){
                return ResponseEntity.badRequest().build();
            }
            if(pUEMAIL.containsKey("error")){
                return ResponseEntity.badRequest().build();
            }
            if(pUPASSWORD.containsKey("error")){
                return ResponseEntity.badRequest().build();
            }

            boolean hasRecuperationEmail = false;
            Properties pURecuperationEmail = Validator.valide(Validator.CompiledPatterns.EMAIL_V1.getValue(), entity.getRecuperationEmail());
            if(entity.getRecuperationEmail() != null){
                hasRecuperationEmail = true;
                if(pURecuperationEmail.containsKey("error")){
                    return ResponseEntity.badRequest().build();
                }
            }

            boolean hasRecuperationPhone = false;
            Properties pUPHONE;
            if(entity.getRecuperationPhone() != null){
                hasRecuperationPhone = true;
                switch (entity.getCountry()) {
                    case Country.BRAZIL:
                        pUPHONE =  Validator.valide(Validator.CompiledPatterns.PHONE_BR.getValue(), entity.getRecuperationPhone());
                        if(pUPHONE.containsKey("error")){
                            return ResponseEntity.badRequest().build();
                        }else break;
                    default:
                        return ResponseEntity.internalServerError().build();
                }
            }

            if (userType.equals(UserType.CIVIL.toString())) {
                if(hasRecuperationPhone && hasRecuperationEmail){
                    User u = new CivilUser(null, entity.getAccountCreation(), entity.getByname(), entity.getEmail(), entity.getPassword(), entity.getRecuperationEmail(), entity.getRecuperationPhone(), entity.getAccountType(), entity.getCountry(), entity.getName(), entity.getCpf(), entity.getBirthday());
                    return ResponseEntity.ok().body(service.insert(u));
                }else{
                    User u = new CivilUser(null, entity.getAccountCreation(), entity.getByname(), entity.getEmail(), entity.getPassword(), entity.getAccountType(), entity.getCountry(), entity.getName(), entity.getCpf(), entity.getBirthday());
                    return ResponseEntity.ok().body(service.insert(u));
                }
            }else if(userType.equals(UserType.JURIDIC.toString())){
                if(hasRecuperationEmail && hasRecuperationPhone){
                    User u = new JuridicUser(null, entity.getAccountCreation(), entity.getByname(), entity.getEmail(), entity.getPassword(), entity.getRecuperationEmail(), entity.getRecuperationPhone(), entity.getCnpj(), entity.getName(), entity.getCountry());
                    return ResponseEntity.ok().body(u);
                }else{
                    User u = new JuridicUser(null, entity.getAccountCreation(), entity.getByname(), entity.getEmail(), entity.getPassword(), entity.getCnpj(), entity.getName(), entity.getCountry());
                    return ResponseEntity.ok().body(u);
                }
            }else if(userType.equals(UserType.UNDEFINED.toString())){
                if(hasRecuperationEmail && hasRecuperationPhone){
                    User u = new User(null, entity.getAccountCreation(), entity.getByname(), entity.getEmail(), entity.getPassword(), entity.getRecuperationEmail(), entity.getRecuperationPhone(), UserType.UNDEFINED, entity.getCountry());
                    return ResponseEntity.ok().body(u);
                }else{
                    User u = new User(null, entity.getAccountCreation(), entity.getByname(), entity.getEmail(), entity.getPassword(), UserType.UNDEFINED, entity.getCountry());
                    return ResponseEntity.ok().body(u);
                }
            }
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("login")
    public ResponseEntity<User> login(@RequestBody User entity) {
        if(!entity.equals(null)){
            if(entity.getByname().equals(null) && entity.getEmail().equals(null)){
                throw new NullPointerException("Email and Byname cannot be null. Insert one of these values.");
            }

            if(entity.getPassword().equals(null)){
                throw new NullPointerException("Password cannot be null.");
            }

            Properties pUPASSWORD =  Validator.valide(Validator.CompiledPatterns.PASSWORD.getValue(), entity.getPassword());
            
            if(pUPASSWORD.containsKey("error")){
                return ResponseEntity.badRequest().build();
            }

            if(entity.getByname().equals(null)){
                Properties pUNAME =  Validator.valide(Validator.CompiledPatterns.USER_NAME.getValue(), entity.getByname());
                if(pUNAME.containsKey("error")){
                    return ResponseEntity.badRequest().build();
                }
                
                String password = pUPASSWORD.getProperty("result");
                String byname = pUNAME.getProperty("result");

                if(service.existsByByname(byname)){
                    User u = service.findByByname(byname);
                    if(u.getPassword().equals(password)){
                        return ResponseEntity.ok().body(u);
                    }
                }else{
                    throw new UserNotFoundException("byname", byname);
                }
            }else if(entity.getEmail().equals(null)){
                Properties pUEMAIL =  Validator.valide(Validator.CompiledPatterns.EMAIL_V1.getValue(), entity.getEmail());
                if(pUEMAIL.containsKey("error")){
                    return ResponseEntity.badRequest().build();
                }

                String password = pUPASSWORD.getProperty("result");
                String email = pUEMAIL.getProperty("result");

                if(service.existsByEmail(email)){
                    User u = service.findByEmail(email);
                    if(u.getPassword().equals(password)){
                        return ResponseEntity.ok().body(u);
                    }
                }else{
                    throw new UserNotFoundException("email", email);
                }
            }
        }else{
            throw new NullPointerException("Invalid User passed as argument.");
        }
        return ResponseEntity.internalServerError().build();
    }
    
}
