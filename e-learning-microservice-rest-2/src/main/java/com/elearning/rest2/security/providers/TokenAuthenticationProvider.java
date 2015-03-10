package com.elearning.rest2.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.elearning.rest2.security.services.TokenService;
import com.google.common.base.Optional;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    private com.elearning.rest2.security.services.TokenService tokenService;

    public TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> token = (Optional) authentication.getPrincipal();
        if (!token.isPresent() || token.get().isEmpty()) {
            throw new BadCredentialsException("Invalid token");
        }
        if (!tokenService.contains(token.get())) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        return tokenService.retrieve(token.get());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
