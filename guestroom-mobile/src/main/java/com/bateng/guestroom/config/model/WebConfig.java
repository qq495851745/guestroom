package com.bateng.guestroom.config.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("webConfig")
@PropertySource("classpath:config.properties")
//@ConfigurationProperties(prefix = "guestroom.web")
public class WebConfig {
    @Value("${guestroom.web.url}")
    private String url;

    @Value("${photo.ftp.url}")
    private  String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
