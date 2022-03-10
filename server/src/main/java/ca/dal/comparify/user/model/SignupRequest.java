package ca.dal.comparify.user.model;


import ca.dal.comparify.utils.StringUtils;

import java.util.Set;

public class SignupRequest {

  private String firstName;

  private String lastName;

  private String username;

  private String email;

  private String password;

  private String confirmPassword;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public boolean validate(){

    if(StringUtils.isAnyEmpty(firstName, lastName, password, confirmPassword, email)){
      return false;
    }

    if(!password.equals(confirmPassword)){
      return false;
    }

    // password length
    // password combination

    // email pattern

    return true;
  }

}
