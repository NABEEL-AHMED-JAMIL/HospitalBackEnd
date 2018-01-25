package com.ballistic.hospital.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Nabeel on 8/23/2017.
 */
@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {

  private String url;
  private String secret;
  private String site;

  public CaptchaSettings() {}

  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  public String getSecret() {
    return secret;
  }
  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getSite() { return site; }
  public void setSite(String site) {
    this.site = site;
  }

  @Override
  public String toString() {
    return "CaptchaSettings {" + "url='" + url + '\'' + ", secret='" + secret + '\'' +
            ", site='" + site + '\'' + '}';
  }
}
