package com.ballistic.hospital.captcha;
// "03AO6mBfxfyDqcETbv5Fzh0oyXLQkbgMzNptNKf6XFBHzOP27IIfplPrFu2iHFJek-
// Bt5mrhg4PvUeSJCeqDecK0ozknzsnqMGrMKYNb40unkw62fwYUgFQSAw-
// BejmqKs6gOLhJI6VMnEkw28IiNftNWGWFNG6ryiCYVX_lI2a4rWPf-0pCUS4qaiaH8T4sh3XjGQIowERx9AdfwRcYPYxtMkoGsf69bEW-
// XLokq61KhfrSAsbq13tbEaua-r3b1k440Yp99Jc35zzAQsWFVapEtqBYW4B_66Z-iY0-
// ricikomQkIfjuZzdfnwqRjuT5J4uK6p-n5fiBjDleAVCZbVFu-
// SNDfoh3NDZqI92ciIoPPxOpIOGSBJdS3AS5-ui6oKtCQlojBUMqjWjU6VNnzwx7tJE172V_bxw"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
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


  private RestOperations restTemplate;

  private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

  @Override
  public void processResponse(final String token) {
    LOGGER.debug("Attempting to validate response {}", token);

    if (reCaptchaAttemptService.isBlocked(getClientIP())) {
      throw new ReCaptchaInvalidException("Client exceeded maximum number of failed attempts");
    }

    // check the token....
    if (!responseSanityCheck(token)) {
      throw new ReCaptchaInvalidException("Response contains invalid characters");
    }

    final URI verifyUri = URI.create(String.format
      ("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s", getReCaptchaSecret(),
        token, getClientIP()));

    try {

      final GoogleResponse googleResponse = this.restTemplate.getForObject(verifyUri, GoogleResponse.class);
      LOGGER.debug("Google's response: {} ", googleResponse.toString());

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

  private boolean responseSanityCheck(final String response) {
    // pattern
    return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
  }

  @Override
  public String getReCaptchaSite() {
    return captchaSettings.getSite();
  }

  @Override
  public String getReCaptchaSecret() {
    return captchaSettings.getSecret();
  }

  private String getClientIP() {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}
