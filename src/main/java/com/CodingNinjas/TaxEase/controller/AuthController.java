package com.CodingNinjas.TaxEase.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingNinjas.TaxEase.dto.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user) {
		AuthResponse authResponse = new AuthResponse();
		authResponse.setUserId(user.getEmail());
		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
		authResponse
				.setAuthorities(user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList()));
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

}
