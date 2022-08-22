package com.fincity.security.dto;

import org.jooq.types.ULong;

import com.fincity.saas.commons.model.dto.AbstractUpdatableDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractUpdatableDTO<ULong, ULong> {

	private static final long serialVersionUID = -7176719786090846975L;

	private ULong clientId;
	private String name;
	private String description;
}
