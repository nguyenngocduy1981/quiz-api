package net.quiz.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.quiz.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

  private Integer id;

  private String userName;

  private String typeRole;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Integer id, String username, String password, String typeRole, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.userName = username;
    this.typeRole = typeRole;
    this.authorities = authorities;
    this.password = password;

  }

  public static UserPrincipal create(User user, String pass) {
    String roleName = user.getRole();
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(roleName));

    return new UserPrincipal(
            user.getId(),
            user.getName(),
            pass,
            roleName,
            authorities);
  }

  public Integer getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String getTypeRole() {
    return typeRole;
  }

  public void setTypeRole(String typeRole) {
    this.typeRole = typeRole;
  }

  public void setUsername(String username) {
    this.userName = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
