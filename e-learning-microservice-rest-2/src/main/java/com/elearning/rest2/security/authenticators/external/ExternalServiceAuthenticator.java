package com.elearning.rest2.security.authenticators.external;

import com.elearning.rest2.security.authentications.AuthenticationWithToken;

public interface ExternalServiceAuthenticator {

    AuthenticationWithToken authenticate(String username, String password);
}
