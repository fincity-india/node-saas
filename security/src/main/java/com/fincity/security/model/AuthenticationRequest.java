package com.fincity.security.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jooq.types.ULong;

@Data
@Accessors(chain = true)
public class AuthenticationRequest {

	private String userName;
	private String password;
	private ULong userId;

	private String socialRegisterState;

	private AuthenticationIdentifierType identifierType;
	private boolean rememberMe = false;
	private boolean cookie = false;
}
