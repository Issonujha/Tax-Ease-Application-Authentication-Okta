package com.CodingNinjas.TaxEase.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String userId;
	private String accessToken;
	private long expireAt;
	private Collection<String> authorities;
}
