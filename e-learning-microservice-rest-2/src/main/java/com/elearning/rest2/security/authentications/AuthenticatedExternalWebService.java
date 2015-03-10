package com.elearning.rest2.security.authentications;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.elearning.rest2.security.authenticators.external.ExternalWebServiceStub;

public class AuthenticatedExternalWebService extends AuthenticationWithToken {

    private ExternalWebServiceStub externalWebService;

    public AuthenticatedExternalWebService(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities) {
        super(aPrincipal, aCredentials, anAuthorities);
    }

    public void setExternalWebService(ExternalWebServiceStub externalWebService) {
        this.externalWebService = externalWebService;
    }

    public ExternalWebServiceStub getExternalWebService() {
        return externalWebService;
    }
}
