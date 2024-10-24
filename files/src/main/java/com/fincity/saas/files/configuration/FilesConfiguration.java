package com.fincity.saas.files.configuration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;

import com.fincity.nocode.reactor.util.FlatMapUtil;
import com.fincity.saas.commons.jooq.configuration.AbstractJooqBaseConfiguration;
import com.fincity.saas.commons.security.ISecurityConfiguration;
import com.fincity.saas.commons.security.service.FeignAuthenticationService;
import com.fincity.saas.commons.util.LogUtil;

import jakarta.annotation.PostConstruct;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;

@Configuration
public class FilesConfiguration extends AbstractJooqBaseConfiguration
		implements ISecurityConfiguration {

	@Override
	@PostConstruct
	public void initialize() {
		super.initialize();
		Logger log = LoggerFactory.getLogger(FlatMapUtil.class);
		FlatMapUtil.setLogConsumer(signal -> LogUtil.logIfDebugKey(signal, (name, v) -> {

			if (name != null)
				log.debug("{} - {}", name, v);
			else
				log.debug(v);
		}));
	}

	@Bean
	SecurityWebFilterChain filterChain(ServerHttpSecurity http,
			FeignAuthenticationService authService) {
		ServerWebExchangeMatcher matcher = new OrServerWebExchangeMatcher(
				new PathPatternParserServerWebExchangeMatcher("/api/files/static/file/**"),
				new PathPatternParserServerWebExchangeMatcher("/api/files/secured/file/**"),
				new PathPatternParserServerWebExchangeMatcher(
						"/api/files/secured/downloadFileByKey/*"));

		return this.springSecurityFilterChain(http, authService, this.objectMapper, matcher,

				"/api/files/static/file/**", "/api/files/internal/**", "/api/files/secured/downloadFileByKey/*");
	}

	@Bean
	ReactiveHttpRequestInterceptor feignInterceptor() {
		return request -> Mono.deferContextual(ctxView -> {

			if (ctxView.hasKey(LogUtil.DEBUG_KEY)) {
				String key = ctxView.get(LogUtil.DEBUG_KEY);

				request.headers().put(LogUtil.DEBUG_KEY, List.of(key));
			}

			return Mono.just(request);
		});
	}

}
