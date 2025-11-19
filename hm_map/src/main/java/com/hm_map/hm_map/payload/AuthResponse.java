package com.hm_map.hm_map.payload;

public class AuthResponse {
  private String token;

  public AuthResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
