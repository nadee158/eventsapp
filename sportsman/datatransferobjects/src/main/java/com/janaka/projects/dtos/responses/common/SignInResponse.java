package com.janaka.projects.dtos.responses.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import com.janaka.projects.common.security.User;



public class SignInResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private long suid = 0;

  private String suuid = StringUtils.EMPTY;

  private String uN = StringUtils.EMPTY;

  private String fN = StringUtils.EMPTY;

  private List<String> aulst = new ArrayList<String>();

  public SignInResponse(User user) {
    super();
    this.suid = user.getId();
    this.suuid = user.getSecurityUserId().toString();
    this.uN = user.getUserName();
    this.fN = user.getUserFullName();
    this.aulst = constructAulst(user.getAuthorities());
  }

  private List<String> constructAulst(Collection<? extends GrantedAuthority> authorities) {
    List<String> auList = new ArrayList<String>();
    if (!(authorities == null)) {
      for (GrantedAuthority auth : authorities) {
        auList.add(auth.getAuthority());
      }
    }
    return auList;
  }

  public long getSuid() {
    return suid;
  }

  public void setSuid(long suid) {
    this.suid = suid;
  }

  public String getSuuid() {
    return suuid;
  }

  public void setSuuid(String suuid) {
    this.suuid = suuid;
  }

  public String getuN() {
    return uN;
  }

  public void setuN(String uN) {
    this.uN = uN;
  }

  public String getfN() {
    return fN;
  }

  public void setfN(String fN) {
    this.fN = fN;
  }

  public List<String> getAulst() {
    return aulst;
  }

  public void setAulst(List<String> aulst) {
    this.aulst = aulst;
  }



}
