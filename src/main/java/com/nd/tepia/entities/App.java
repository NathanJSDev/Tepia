package com.nd.tepia.entities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.aspectj.util.FileUtil;

import com.nd.tepia.TepiaApplication;
import com.nd.tepia.entities.enums.Language;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_app")
public class App implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Size(max = 100000)
    private String tinyIconLink;
    @Size(max = 250000)
    private String normalIconLink;
    @Size(max = 500000)
    private String bigIconLink;
    @Size(max = 5000)
    private String description;
    private Double price;

    @OneToMany
    private List<AppVersion> versions = new ArrayList<>();

    @ManyToOne
    private User creator;

    @OneToMany
    private List<AppFeedback> feedBacks = new ArrayList<>();

    private List<Double> languagesSupport = new ArrayList<>();

    public App() {
    }

    public App(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description.replaceAll("\n","<br>");
        this.price = price;
    }

    public App(Long id, String name, String tinyIconLink, String normalIconLink, String bigIconLink, String description,
            Double price) {
        this.id = id;
        this.name = name;
        this.tinyIconLink = tinyIconLink;
        this.normalIconLink = normalIconLink;
        this.bigIconLink = bigIconLink;
        this.description = description.replaceAll("\n","<br>");
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTinyIconLink() {
        return tinyIconLink;
    }

    public void setTinyIconLink(String tinyIconLink) {
        this.tinyIconLink = tinyIconLink;
    }

    public String getNormalIconLink() {
        return normalIconLink;
    }

    public void setNormalIconLink(String normalIconLink) {
        this.normalIconLink = normalIconLink;
    }

    public String getBigIconLink() {
        return bigIconLink;
    }

    public void setBigIconLink(String bigIconLink) {
        this.bigIconLink = bigIconLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("\n","<br>");
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Double> getLanguagesSupport() {
        return languagesSupport;
    }

    public void addLanguageSupport(Language languageSupport) {
        languagesSupport.add(languageSupport.getCode());
    }

    public List<AppVersion> getVersions() {
        return versions;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<AppFeedback> getFeedBacks() {
        return feedBacks;
    }

    public void addFeedback(AppFeedback feedback) {
        feedBacks.add(feedback);
    }

    public void removeFeedback(AppFeedback feedback) {
        feedBacks.remove(feedback);
    }

    private Integer[] getFeedBackRate() {
        Integer[] ratesCount = new Integer[feedBacks.size()];
        Integer[] rates = new Integer[6];
        rates[0] = 0;
        rates[1] = 0;
        rates[2] = 0;
        rates[3] = 0;
        rates[4] = 0;
        for (int i = 0; i < feedBacks.size(); i++) {
            ratesCount[i] = feedBacks.get(i).getRate();
            if (ratesCount[i] == 5) {
                rates[0] += 1;
            } else if (ratesCount[i] == 4) {
                rates[1] += 1;
            } else if (ratesCount[i] == 3) {
                rates[2] += 1;
            } else if (ratesCount[i] == 2) {
                rates[3] += 1;
            } else if (ratesCount[i] == 1) {
                rates[4] += 1;
            }
        }
        rates[5] = feedBacks.size();
        return rates;
    }

    public String getPage() {
        if (versions.size() > 0) {
            String base = "";
            String image = "";
            try (BufferedInputStream o = (BufferedInputStream) TepiaApplication.class
                    .getResource("views//base/AppBase.html").getContent()) {
                base = new String(o.readAllBytes(), StandardCharsets.UTF_8);
                File f = new File(TepiaApplication.class.getResource("views/images/nave.png").toURI());
                image = String.format("data:image/png;charset=utf-8;base64,%s",
                        Base64.getEncoder().encodeToString(FileUtil.readAsByteArray(f)));
            } catch (Exception e) {
                return e.getMessage();
            }

            String star = "<ion-icon name='star-outline'></ion-icon>";
            String star_filled = "<ion-icon name='star'></ion-icon>";
            String star_half = "<ion-icon name='star-half-outline'></ion-icon>";

            String html = base;
            html = html.replaceFirst("%s", getDescription());
            html = html.replaceFirst("%s", bigIconLink);
            html = html.replaceFirst("%s", bigIconLink);
            html = html.replaceFirst("%s", getName());
            html = html.replaceFirst("%s", getCreator().getByname());
            html = html.replaceFirst("%s", bigIconLink);

            // App
            html = html.replaceFirst("%s", getDescription());
            html = html.replaceFirst("%s", getName());
            html = html.replaceFirst("%s", getCreator().getByname());
            html = html.replaceFirst("%s", getPrice().toString());
            html = html.replaceFirst("%s", image);

            String fd = getDescription();
            if (fd.length() > 200) {
                fd = fd.substring(0, 200) + "...";
            }

            html = html.replaceFirst("%s", fd);

            Integer[] rates = getFeedBackRate();

            int tv = (rates[0] + rates[1] + rates[2] + rates[3] + rates[4]);
            int avcount = feedBacks.size();

            double pondMedia = (double) (((rates[0] * 5d) + (rates[1] * 4d) + (rates[2] * 3d) + (rates[3] * 2d)
                    + (double) rates[4]) / (double) tv);
            String stars = "";

            if (pondMedia > 0d && pondMedia < 0.25d) {
                pondMedia = 0d;
                stars = star + star + star + star + star;
            } else if (pondMedia > 0.25d && pondMedia < 0.75d) {
                pondMedia = 0.5d;
                stars = star_half + star + star + star + star;
            } else if (pondMedia > 0.75d && pondMedia < 1.25d) {
                pondMedia = 1d;
                stars = star_filled + star + star + star + star;
            } else if (pondMedia > 1.25d && pondMedia < 1.75d) {
                pondMedia = 1.5d;
                stars = star_filled + star_half + star + star + star;
            } else if (pondMedia > 1.75d && pondMedia < 2.25d) {
                pondMedia = 2d;
                stars = star_filled + star_filled + star + star + star;
            } else if (pondMedia > 2.25d && pondMedia < 2.75d) {
                pondMedia = 2.5d;
                stars = star_filled + star_filled + star_half + star + star;
            } else if (pondMedia > 2.75d && pondMedia < 3.25d) {
                pondMedia = 3d;
                stars = star_filled + star_filled + star_filled + star + star;
            } else if (pondMedia > 3.25d && pondMedia < 3.75d) {
                pondMedia = 3.5d;
                stars = star_filled + star_filled + star_filled + star_half + star;
            } else if (pondMedia > 3.75d && pondMedia < 4.25d) {
                pondMedia = 4d;
                stars = star_filled + star_filled + star_filled + star_filled + star;
            } else if (pondMedia > 4.25d && pondMedia < 4.75d) {
                pondMedia = 4.5d;
                stars = star_filled + star_filled + star_filled + star_filled + star_half;
            } else if (pondMedia > 4.75d && pondMedia < 5.00d) {
                pondMedia = 5d;
                stars = star_filled + star_filled + star_filled + star_filled + star_filled;
            }

            html = html.replaceFirst("%s", String.format("%.1f", pondMedia).replace(',', '.'));
            html = html.replaceFirst("%s", stars);
            html = html.replaceFirst("%s", String.valueOf(avcount));

            for (int i = 0; i < (rates.length - 1); i++) {
                double v = ((double) rates[i] / tv) * 100;
                html = html.replaceFirst("%s", String.format("style=\"width: %.0fpx;\"", v * 7));
            }

            String html_feedbacks = "";
            if (feedBacks.size() >= 3) {
                for (int i = 0; i < 3; i++) {
                    html_feedbacks += feedBacks.get(i).getBanner();
                }
            } else {
                for (AppFeedback af : feedBacks) {
                    html_feedbacks += af.getBanner();
                }
            }

            html = html.replaceFirst("%s", html_feedbacks);

            return html;

        } else
            return "";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        App other = (App) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

}
