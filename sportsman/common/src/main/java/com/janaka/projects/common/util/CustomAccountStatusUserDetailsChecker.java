package com.janaka.projects.common.util;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

import com.janaka.projects.common.security.User;

public class CustomAccountStatusUserDetailsChecker implements UserDetailsChecker {

  protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

  public void check(UserDetails userDetails) {
    User user = (User) userDetails;

    if (user.isCustomAccountLocked()) {
      throw new LockedException(
          messages.getMessage("AccountStatusUserDetailsChecker.locked", "User account is locked"));
    }

    if (!user.isCustomAccountEnabled()) {
      throw new DisabledException(messages.getMessage("AccountStatusUserDetailsChecker.disabled", "User is disabled"));
    }

    if (user.isCustomAccountExpired()) {
      throw new AccountExpiredException(
          messages.getMessage("AccountStatusUserDetailsChecker.expired", "User account has expired"));
    }

    if (user.isCustomCredentialsExpired()) {
      throw new CredentialsExpiredException(
          messages.getMessage("AccountStatusUserDetailsChecker.credentialsExpired", "User credentials have expired"));
    }
  }

}
