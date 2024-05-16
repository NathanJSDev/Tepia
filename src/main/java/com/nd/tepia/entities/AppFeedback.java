package com.nd.tepia.entities;

import java.io.BufferedInputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.aspectj.util.FileUtil;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nd.tepia.TepiaApplication;
import com.nd.tepia.entities.PK.AppFeedbackPK;
import com.nd.tepia.entities.reports.AppFeedbackReport;

@Entity
@Table(name = "tb_appfeedbacks")
public class AppFeedback implements Serializable{
    private static final long serialVersionUID = 1l;
    
    @EmbeddedId
    private AppFeedbackPK id = new AppFeedbackPK();
    private String text;
    private Integer rate;
    private Instant date;

    private Integer usefullCount = 0;
    private Integer nonUsefullCount = 0;
    
    @OneToMany
    private List<AppFeedbackReport> reports = new ArrayList<>();

    public AppFeedback(){

    }

    public AppFeedback(User user, App app, String text, Integer rate, Instant date){
        super();
        id.setUser(user);
        id.setApp(app);
        this.text = text;
        this.rate = rate;
        this.date = date;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Integer getRate() {
        return rate;
    }
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @JsonIgnore
    public User getUser() {
        return id.getUser();
    }
    public void setUser(User user) {
        id.setUser(user);
    }
    @JsonIgnore
    public App getApp() {
        return id.getApp();
    }
    public void setApp(App app) {
        id.setApp(app);
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getUsefullCount() {
        return usefullCount;
    }

    public void setUsefullCount(Integer usefullCount) {
        if(usefullCount>=0)
            this.usefullCount = usefullCount;
    }

    /**
     * @return The UsefullCount Property
    */
    public Integer plusUsefull(){
        usefullCount = usefullCount+1;
        return usefullCount;
    }

    /**
     * @return The UsefullCount Property
    */
    public Integer minusUsefull(){
        usefullCount = usefullCount-1;
        return usefullCount;
    }

    public Integer getNonUsefullCount() {
        return nonUsefullCount;
    }

    public void setNonUsefullCount(Integer nonUsefullCount) {
        if (nonUsefullCount>=0)
            this.nonUsefullCount = nonUsefullCount;
    }

    /**
     * @return The NonUsefullCount Property
    */
    public Integer plusNonUsefull(){
        nonUsefullCount = nonUsefullCount+1;
        return nonUsefullCount;
    }

    /**
     * @return The NonUsefullCount Property
    */
    public Integer minusNonUsefull(){
        nonUsefullCount = nonUsefullCount-1;
        return nonUsefullCount;
    }

    public List<AppFeedbackReport> getReports() {
        return reports;
    }    

    private String getRateAsStars(){
        String stars = "";

        String star = "<ion-icon name='star-outline'></ion-icon>";
        String star_filled = "<ion-icon name='star'></ion-icon>";

        if(rate==5)stars = star_filled + star_filled + star_filled + star_filled + star_filled;
        if(rate==4)stars = star_filled + star_filled + star_filled + star_filled + star;
        if(rate==3)stars = star_filled + star_filled + star_filled + star + star;
        if(rate==2)stars = star_filled + star_filled + star + star + star;
        if(rate==1)stars = star_filled + star + star + star + star;

        return stars;
    }
    
    public String getBanner(){
        String base = "";
        String image = "";
        try (BufferedInputStream o = (BufferedInputStream) TepiaApplication.class.getResource("views/base/AppFeedbackBase.html").getContent()) {
                base = new String(o.readAllBytes(), StandardCharsets.UTF_8);
                File f = new File(TepiaApplication.class.getResource("views/images/Logo1.2.png").toURI());
                image = String.format("data:image/png;charset=utf-8;base64,%s",Base64.getEncoder().encodeToString(FileUtil.readAsByteArray(f)));
        }catch(Exception e){e.printStackTrace();};

        Locale.getDefault();

        LocalDate ld = LocalDate.ofInstant(date, ZoneId.systemDefault());
        String formattedDate = ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        base = base.replaceFirst("%s", image);
        base = base.replaceFirst("%s", id.getUser().getByname());
        base = base.replaceFirst("%s", getRateAsStars());
        base = base.replaceFirst("%s", formattedDate);
        base = base.replaceFirst("%s", getText());

        String usefullC = "";
        if(getUsefullCount()!=0) usefullC = "<span>" + getUsefullCount() + "</span> users thinks this comment is useful!";
        base = base.replaceFirst("%s", usefullC);
        base = base.replaceFirst("%s", String.format("[%d,%d]", getUser().getId(), getApp().getId()));
        base = base.replaceFirst("%s", String.format("[%d,%d]", getUser().getId(), getApp().getId()));

        return base;
    }
}
