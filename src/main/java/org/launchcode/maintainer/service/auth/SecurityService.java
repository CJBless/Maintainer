package org.launchcode.maintainer.service.auth;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
