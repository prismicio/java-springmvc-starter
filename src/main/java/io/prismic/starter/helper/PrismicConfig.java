package io.prismic.starter.helper;

public class PrismicConfig {

  private String apiEndpoint;
  private String accessToken;
  private String clientId;
  private String clientSecret;

  public void setApiEndpoint(String apiEndpoint) {
    this.apiEndpoint = apiEndpoint;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getApiEndpoint() {
    return apiEndpoint;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

}