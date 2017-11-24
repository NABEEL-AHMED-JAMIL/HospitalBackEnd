package com.ballistic.hospital.captcha;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nabeel on 8/23/2017.
 */
@Service("reCaptchaAttemptService")
public class ReCaptchaAttemptService {

  private final Integer MAX_ATTEMPT = 4;
  private LoadingCache<String, Integer> attemptsCache;

  public ReCaptchaAttemptService() {
    super();
    attemptsCache = CacheBuilder.newBuilder().
      expireAfterWrite(4, TimeUnit.HOURS).build(new CacheLoader<String, Integer>() {
      @Override
      public Integer load(final String key) {
        return 0;
      }
    });
  }

  public void reCaptchaSucceeded(final String key) {
    attemptsCache.invalidate(key);
  }

  public void reCaptchaFailed(final String key) {
    int attempts = attemptsCache.getUnchecked(key);
    attempts++;
    attemptsCache.put(key, attempts);
  }

  public boolean isBlocked(final String key) {
    return attemptsCache.getUnchecked(key) >= MAX_ATTEMPT;
  }
}
