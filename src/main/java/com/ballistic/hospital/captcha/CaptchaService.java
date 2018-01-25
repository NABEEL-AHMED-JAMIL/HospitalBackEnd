package com.ballistic.hospital.captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Service("captchaService")
public class CaptchaService implements ICaptchaService {

  private final static Logger LOGGER = LoggerFactory.getLogger(CaptchaService.class);

  @Autowired
  private HttpServletRequest request;
  @Autowired
  private CaptchaSettings captchaSettings;
  @Autowired
  private ReCaptchaAttemptService reCaptchaAttemptService;

  private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

  @Override
  public void processResponse(final String response) {

    LOGGER.debug("Attempting to validate response {}", response);

    try {

      isBlockedIp();
      responseCheckValidChaarcters(response);

      MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
      form.add("secret", getReCaptchaSecret());
      form.add("response", response);
      form.add("remoteip", getClientIP());

      GoogleResponse googleResponse = getRestTemplate().postForObject(getReCaptchaUrl(), form ,GoogleResponse.class);
      LOGGER.info("Google's response: {} ", googleResponse.toString());

      if (!googleResponse.isSuccess()) {
        if (googleResponse.hasClientError()) {
          reCaptchaAttemptService.reCaptchaFailed(getClientIP());
        }
        throw new ReCaptchaInvalidException("reCaptcha was not successfully validated");
      }
    } catch (RestClientException rce) {
      throw new ReCaptchaUnavailableException("Registration unavailable at this time.  Please try again later.", rce);
    }

    reCaptchaAttemptService.reCaptchaSucceeded(getClientIP());

  }

  @Override
  public String getReCaptchaSite() {
    return captchaSettings.getSite();
  }
  @Override
  public String getReCaptchaSecret() {
    return captchaSettings.getSecret();
  }
  @Override
  public String getReCaptchaUrl() { return captchaSettings.getUrl(); }


  private String getClientIP() {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }

  private void isBlockedIp() {
    if (reCaptchaAttemptService.isBlocked(getClientIP())) {
      throw new ReCaptchaInvalidException("Client exceeded maximum number of failed attempts");
    }
  }

  private void responseCheckValidChaarcters(final String response) {
    // check the token....
    if (!responseSanityCheck(response)) {
      throw new ReCaptchaInvalidException("Response contains invalid characters");
    }
  }

  private boolean responseSanityCheck(final String response) {
    return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
  }

  private RestTemplate getRestTemplate() { return  new RestTemplate(); }

}
