package com.hr.pr.domain;

import org.eclipse.egit.github.core.client.GitHubClient;

public class TokenAuth implements Authenticator {

    private String token;

    public TokenAuth(String token) {
        this.token = token;
    }

    @Override
    public void authenticate(GitHubClient client) {
        client.setOAuth2Token(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


