package com.janaka.projects.common.security;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class SecurityHelper {

  public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

  private static final String WINDOWS = "Windows";
  private static final String MAC = "Mac";
  private static final String UNIX = "Unix";
  private static final String ANDROID = "Android";
  private static final String IPHONE = "IPhone";

  private static final String SECURITY_CONTEXT = "SECURITY_CONTEXT";
  private static final String AUDIT_CONTEXT = "AUDIT_CONTEXT";


  public static SecurityContext getSecurityContext() {
    HttpServletRequest request = getRequest();
    if (!(request == null)) {
      SecurityContext securityContext = null;
      if (request.getAttribute(SECURITY_CONTEXT) == null) {
        securityContext = new SecurityContext(getRequest().getHeader(AUTH_HEADER_NAME));
        request.setAttribute(SECURITY_CONTEXT, securityContext);
      }
      return (SecurityContext) request.getAttribute(SECURITY_CONTEXT);
    }
    return null;
  }

  public static AuditContext getAuditContext() {
    HttpServletRequest request = getRequest();
    if (!(request == null)) {
      AuditContext auditContext = null;
      if (request.getAttribute(AUDIT_CONTEXT) == null) {
        auditContext = new AuditContext();
        auditContext.setClientIPAddress(getClientIPAddress(request));
        auditContext.setUserAgent(getUserAgent(request));
        auditContext.setOs(getOs(auditContext.getUserAgent()));
        auditContext.setMobile(checkIfMobile(auditContext.getOs()));
        auditContext.setBrowser(getBrowser(auditContext.getUserAgent()));
        auditContext.setServerIPAddress(request.getRemoteAddr());
        auditContext.setTimestamp(Calendar.getInstance().getTime());
        request.setAttribute(AUDIT_CONTEXT, auditContext);
      }
      return (AuditContext) request.getAttribute(AUDIT_CONTEXT);
    }
    return null;
  }


  private static HttpServletRequest getRequest() {
    if (!(RequestContextHolder.getRequestAttributes() == null)) {
      ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      return sra.getRequest();
    }
    return null;
  }

  private static String getClientIPAddress(HttpServletRequest request) {
    String ipAddress = request.getHeader("X-FORWARDED-FOR");
    if (ipAddress == null) {
      ipAddress = request.getRemoteAddr();
    }
    return ipAddress;
  }

  private static String getUserAgent(HttpServletRequest request) {
    return request.getHeader("User-Agent");
  }

  private static String getOs(String userAgent) {
    if (StringUtils.isNotEmpty(userAgent)) {
      String os = "";
      // =================OS=======================
      if (userAgent.toLowerCase().indexOf("windows") >= 0) {
        os = WINDOWS;
      } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
        os = MAC;
      } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
        os = UNIX;
      } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
        os = ANDROID;
      } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
        os = IPHONE;
      } else {
        os = "UnKnown, More-Info: " + userAgent;
      }
      return os;
    }
    return null;
  }


  private static String getBrowser(String userAgent) {
    if (StringUtils.isNotEmpty(userAgent)) {
      String user = userAgent.toLowerCase();
      String browser = "";
      // ===============Browser===========================
      if (user.contains("msie")) {
        String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
        browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
      } else if (user.contains("safari") && user.contains("version")) {
        browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
            + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
      } else if (user.contains("opr") || user.contains("opera")) {
        if (user.contains("opera"))
          browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
              + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        else if (user.contains("opr"))
          browser =
              ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
      } else if (user.contains("chrome")) {
        browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
      } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
          || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
          || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
        // browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
        browser = "Netscape-?";

      } else if (user.contains("firefox")) {
        browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
      } else if (user.contains("rv")) {
        browser = "IE";
      } else {
        browser = "UnKnown, More-Info: " + userAgent;
      }
      return browser;
    }
    return null;
  }

  private static boolean checkIfMobile(String os) {
    boolean isMobile = false;
    if (StringUtils.equals(os, ANDROID)) {
      isMobile = true;
    } else if (StringUtils.equals(os, IPHONE)) {
      isMobile = true;
    }
    return isMobile;
  }

}
