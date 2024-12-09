package com.fincity.saas.core.service.connection.rest;

import java.util.EnumMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fincity.nocode.reactor.util.FlatMapUtil;
import com.fincity.saas.commons.security.util.SecurityContextUtil;
import com.fincity.saas.commons.util.CommonsUtil;
import com.fincity.saas.commons.util.LogUtil;
import com.fincity.saas.core.dto.RestRequest;
import com.fincity.saas.core.dto.RestResponse;
import com.fincity.saas.core.enums.ConnectionSubType;
import com.fincity.saas.core.enums.ConnectionType;
import com.fincity.saas.core.service.ConnectionService;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.function.Tuples;

@Service
public class RestService {

	private final ConnectionService connectionService;
	private final BasicRestService basicRestService;
	private final OAuth2RestService oAuth2RestService;
	private final RestAuthService restAuthService;

	public RestService(ConnectionService connectionService, BasicRestService basicRestService,
			OAuth2RestService oAuth2RestService, RestAuthService restAuthService) {
		this.connectionService = connectionService;
		this.basicRestService = basicRestService;
		this.oAuth2RestService = oAuth2RestService;
		this.restAuthService = restAuthService;
	}

	private final EnumMap<ConnectionSubType, IRestService> services = new EnumMap<>(ConnectionSubType.class);

	@PostConstruct
	public void init() {
		this.services.put(ConnectionSubType.REST_API_BASIC, basicRestService);
		this.services.put(ConnectionSubType.REST_API_OAUTH2, oAuth2RestService);
		this.services.put(ConnectionSubType.REST_API_AUTH, restAuthService);
	}

	public Mono<RestResponse> doCall(String appCode, String clientCode, String connectionName, RestRequest request) {

		return this.doCall(appCode, clientCode, connectionName, request, false);

	}

	public Mono<RestResponse> doCall(String appCode, String clientCode, String connectionName, RestRequest request,
			boolean fileDownload) {

		return FlatMapUtil.flatMapMono(

				() -> SecurityContextUtil.getUsersContextAuthentication()
						.map(e -> Tuples.of(
								CommonsUtil.nonNullValue(appCode.trim().length() == 0 ? null : appCode,
										e.getUrlAppCode()),
								CommonsUtil.nonNullValue(clientCode.trim().length() == 0 ? null : clientCode,
										e.getUrlClientCode()))),

				codeTuple -> connectionService
						.read(connectionName, codeTuple.getT1(), codeTuple.getT2(), ConnectionType.REST_API),
				(codeTuple, connection) -> Mono.just(
						this.services.get(connection != null ? connection.getConnectionSubType()
								: ConnectionSubType.REST_API_BASIC)),
				(codeTuple, connection, service) -> service.call(connection, request, fileDownload)

		).contextWrite(Context.of(LogUtil.METHOD_NAME, "RestService.doCall"))
				.switchIfEmpty(Mono.defer(() -> Mono.just(
						new RestResponse().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
								.setData("Connection Not found"))));

	}

}
