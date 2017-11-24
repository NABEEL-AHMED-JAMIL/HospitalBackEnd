package com.ballistic.hospital.captcha;

/**
 * Created by Nabeel on 8/23/2017.
 */
public final class ReCaptchaUnavailableException extends RuntimeException {

  private static final long serialVersionUID = 5861310537366287163L;

  public ReCaptchaUnavailableException() {
    super();
  }

  public ReCaptchaUnavailableException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ReCaptchaUnavailableException(final String message) {
    super(message);
  }

  public ReCaptchaUnavailableException(final Throwable cause) {
    super(cause);
  }

}
