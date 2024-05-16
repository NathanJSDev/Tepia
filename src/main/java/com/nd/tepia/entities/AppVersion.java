package com.nd.tepia.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_app_version")
public class AppVersion implements Serializable{
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String version;
    private String versionTag;
    private String srcPath;

    @ManyToOne
    @JsonIgnore
    private App associatedApp;

    public AppVersion(){}

    public AppVersion(Long id, String version, String versionTag, String srcPath, App associatedApp) {
        this.id = id;
        this.version = version;
        this.versionTag = versionTag;
        this.srcPath = srcPath;
        this.associatedApp = associatedApp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public App getAssociatedApp() {
        return associatedApp;
    }

    public void setAssociatedApp(App associatedApp) {
        this.associatedApp = associatedApp;
    }

    


}
