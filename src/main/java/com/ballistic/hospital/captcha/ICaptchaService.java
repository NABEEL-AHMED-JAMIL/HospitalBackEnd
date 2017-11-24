package com.ballistic.hospital.captcha;

/**
 * Created by Nabeel on 8/23/2017.
 */
public interface ICaptchaService {

  void processResponse(final String response) throws ReCaptchaInvalidException;

  String getReCaptchaSite();

  String getReCaptchaSecret();
}
