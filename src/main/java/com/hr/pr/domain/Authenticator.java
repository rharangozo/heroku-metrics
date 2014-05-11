package com.hr.pr.domain;

import org.eclipse.egit.github.core.client.GitHubClient;


public interface Authenticator {
    void authenticate(GitHubClient client);
}
