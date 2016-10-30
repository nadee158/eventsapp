package com.janaka.projects.entitymanagement.domain.usermanagement;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.security.Authority;
import com.janaka.projects.common.security.User;
import com.janaka.projects.entitymanagement.domain.common.BaseDomain;

@Entity
@Table(name = "security_user",
    indexes = {@Index(name = "security_user_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "security_user_security_user_id_index", unique = true, columnList = "uuid"),
        @Index(name = "security_user_user_name_index", unique = true, columnList = "user_name")})
public class SecurityUser extends BaseDomain {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = 0;

  @NotNull
  @Size(min = 4, max = 30)
  @Column(name = "user_name")
  private String userName = StringUtils.EMPTY;

  @NotNull
  @Size(min = 6, max = 20)
  @Column(name = "secret")
  private String secret = StringUtils.EMPTY;

  @Transient
  private long expires = 0;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "person_id")
  private Person person;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "security_user_user_role",
      joinColumns = {@JoinColumn(name = "security_user_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "user_role_id", nullable = false, updatable = false)})
  private Set<UserRole> userRoles = null;

  @Transient
  private Set<Authority> grantedAuthorities;


  @NotNull
  @Column(name = "account_expired")
  private boolean accountExpired = false;

  @NotNull
  @Column(name = "account_locked")
  private boolean accountLocked = false;

  @NotNull
  @Column(name = "credentials_expired")
  private boolean credentialsExpired = false;

  @NotNull
  @Column(name = "account_enabled")
  private boolean accountEnabled = true;



  @Version
  @Column(name = "version_number")
  private long versionNumber = 0;

  public SecurityUser() {

  }

  public SecurityUser(String username, String secret) {
    this.setUserName(username);
    this.setSecret(secret);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }


  public long getExpires() {
    return expires;
  }

  public void setExpires(long expires) {
    this.expires = expires;
  }


  public boolean isAccountExpired() {
    return accountExpired;
  }

  public void setAccountExpired(boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  public boolean isAccountLocked() {
    return accountLocked;
  }

  public void setAccountLocked(boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  public boolean isCredentialsExpired() {
    return credentialsExpired;
  }

  public void setCredentialsExpired(boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
  }

  public boolean isAccountEnabled() {
    return accountEnabled;
  }

  public void setAccountEnabled(boolean accountEnabled) {
    this.accountEnabled = accountEnabled;
  }


  @PostLoad
  public void generateActiveUserRoles() {
    System.out.println("generateAccumilatedGarntedPermissions");
    grantedAuthorities = new HashSet<Authority>();
    if (!(userRoles == null || userRoles.isEmpty())) {
      for (UserRole userRole : userRoles) {
        if (!(userRole == null || userRole.isDeleted())) {
          grantedAuthorities.add(new Authority(userRole.getUserRoleName()));
        }
      }
    }

    System.out.println("this.grantedAuthorities :" + this.grantedAuthorities);
  }


  public User constructUser() {

    return new User(id, UUID.fromString(getUuId()), person.getFullName(), person.getProfileImagePath(),
        person.getDateOfBirth(), person.getMobileNumber(), person.getEmail(), userName, secret, this.grantedAuthorities,
        accountExpired, accountLocked, credentialsExpired, accountEnabled);
  }

  public Set<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }


  public long getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(long versionNumber) {
    this.versionNumber = versionNumber;
  }


  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Set<Authority> getGrantedAuthorities() {
    return grantedAuthorities;
  }

  public void setGrantedAuthorities(Set<Authority> grantedAuthorities) {
    this.grantedAuthorities = grantedAuthorities;
  }

  @Override
  public String toString() {
    return "SecurityUser [id=" + id + ", userName=" + userName + ", secret=" + secret + ", expires=" + expires
        + ", person=" + person + ", userRoles=" + userRoles + ", grantedAuthorities=" + grantedAuthorities
        + ", accountExpired=" + accountExpired + ", accountLocked=" + accountLocked + ", credentialsExpired="
        + credentialsExpired + ", accountEnabled=" + accountEnabled + ", versionNumber=" + versionNumber + "]";
  }



}
