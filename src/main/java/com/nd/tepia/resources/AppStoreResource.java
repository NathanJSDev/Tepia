package com.nd.tepia.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nd.tepia.TepiaApplication;
import com.nd.tepia.resources.formatters.AppFeedbackSearchFormatter;
import com.nd.tepia.services.AppFeedbackService;
import com.nd.tepia.services.AppService;
import com.nd.tepia.services.SessionService;
import com.nd.tepia.services.UserService;
import com.nd.tepia.entities.App;
import com.nd.tepia.entities.AppFeedback;
import com.nd.tepia.entities.Session;
import com.nd.tepia.entities.User;
import com.nd.tepia.entities.PK.AppFeedbackPK;
import com.nd.tepia.entities.enums.Country;
import com.nd.tepia.entities.enums.UserType;

import java.io.BufferedInputStream;
import java.io.File;
import java.time.Instant;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/appstore")
public class AppStoreResource {

    @Autowired
    AppService aService;

    @Autowired
    AppFeedbackService afService;

    @Autowired
    UserService uService;

    @Autowired
    SessionService service;

    @GetMapping
    public String appStore() {
        try {
            return aService.findAll().getFirst().getPage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("apps/{name}")
    public ResponseEntity<String> findByName(@PathVariable String name) {
        App a = aService.findByName(name).get(0);

        return ResponseEntity.ok().body(a.getPage());
    }

    @GetMapping("src/{fileName}")
    public String src(@PathVariable String fileName) {
        try (BufferedInputStream o = (BufferedInputStream) TepiaApplication.class
                .getResource(String.format("views/src/%s", fileName)).getContent()) {
            return new String(o.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("images/png/{fileName}")
    public String imagePNG(@PathVariable String fileName) {
        try {
            File f = new File(TepiaApplication.class.getResource(String.format("views/images/%s", fileName)).toURI());
            String encrypted = Base64.getEncoder().encodeToString(FileUtil.readAsByteArray(f));
            return String.format("data:image/png;charset=utf-8;base64,%s", encrypted);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("images/jpg/{fileName}")
    public String imageJPG(@PathVariable String fileName) {
        try {
            File f = new File(TepiaApplication.class.getResource(String.format("views/images/%s", fileName)).toURI());
            String encrypted = Base64.getEncoder().encodeToString(FileUtil.readAsByteArray(f));
            return String.format("data:image/jpg;charset=utf-8;base64,%s", encrypted);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/afs")
    public String appFeedbackSearch(@RequestBody AppFeedbackSearchFormatter entity) {        
        List<Session> sessions = service.findAll();
        Boolean hasActiveSession = false;
        for (Session session : sessions) {
            if (session.getSessionKey().equals(entity.getSk())) {
                hasActiveSession = true;
                break;
            }
        }
        
        if (hasActiveSession) {
            String[] uaIds = entity.getId().replace("[", "").replace("]", "").split(",");

            AppFeedbackPK afpk = new AppFeedbackPK();
                afpk.setApp(aService.findById(Long.valueOf(uaIds[1])));
                afpk.setUser(uService.findById(Long.valueOf(uaIds[0])));

            AppFeedback af = afService.findById(afpk);

            if (entity.value()) {
                af.plusUsefull();
            } else {
                af.plusNonUsefull();
            }
            afService.save(af);

            return "Thanks for your feedback!";
        } else {
            return "Invalid session!";
        }
    }

    private boolean needsOtherKey = true;
    private String newSessionId = null;

    @GetMapping("/gnsi")
    public Session getNewSessionWithOutUser() {
        List<Session> sessions = service.findAll();

        needsOtherKey = true;
        do {
            newSessionId = Session.generateKey();
            for (Session session : sessions) {
                if (session.getSessionKey() == newSessionId)
                    needsOtherKey = false;
            }
            break;
        } while (needsOtherKey);

        User def = uService.insert(new User(null, Instant.now(), newSessionId, newSessionId, newSessionId, UserType.UNDEFINED, Country.BRAZIL));

        Session persistent_session = service.save(new Session(null, newSessionId, def));
        persistent_session.setActive(true);

        service.save(persistent_session);

        return persistent_session;
    }

}
