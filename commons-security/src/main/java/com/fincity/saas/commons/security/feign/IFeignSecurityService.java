package com.fincity.saas.commons.security.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fincity.saas.common.security.jwt.ContextAuthentication;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "security")
public interface IFeignSecurityService {

	@GetMapping("${security.feign.contextAuthentication:/api/security/internal/securityContextAuthentication}")
	public Mono<ContextAuthentication> contextAuthentication(@RequestHeader("Authorization") String authorization,
	        @RequestHeader("X-Forwarded-Host") String forwardedHost,
	        @RequestHeader("X-Forwarded-Port") String forwardedPort);

	@GetMapping("${security.feign.isBeingManaged:/api/security/clients/internal/isBeingManaged}")
	public Mono<Boolean> isBeingManaged(@RequestParam String managingClientCode, @RequestParam String clientCode);
}