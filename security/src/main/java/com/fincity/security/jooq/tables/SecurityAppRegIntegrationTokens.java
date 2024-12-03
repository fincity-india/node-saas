/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.tables.records.SecurityAppRegIntegrationTokensRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function14;
import org.jooq.Identity;
import org.jooq.JSON;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row14;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SecurityAppRegIntegrationTokens extends TableImpl<SecurityAppRegIntegrationTokensRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>security.security_app_reg_integration_tokens</code>
     */
    public static final SecurityAppRegIntegrationTokens SECURITY_APP_REG_INTEGRATION_TOKENS = new SecurityAppRegIntegrationTokens();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecurityAppRegIntegrationTokensRecord> getRecordType() {
        return SecurityAppRegIntegrationTokensRecord.class;
    }

    /**
     * The column <code>security.security_app_reg_integration_tokens.ID</code>.
     * Primary key
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, ULong> ID = createField(DSL.name("ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.INTEGRATION_ID</code>.
     * Integration ID
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, ULong> INTEGRATION_ID = createField(DSL.name("INTEGRATION_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Integration ID");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.AUTH_CODE</code>. User
     * Consent Auth Code
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, String> AUTH_CODE = createField(DSL.name("AUTH_CODE"), SQLDataType.VARCHAR(512), this, "User Consent Auth Code");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.STATE</code>. Session
     * id for login
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, String> STATE = createField(DSL.name("STATE"), SQLDataType.VARCHAR(512), this, "Session id for login");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.TOKEN</code>. Token
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, String> TOKEN = createField(DSL.name("TOKEN"), SQLDataType.VARCHAR(512), this, "Token");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.REFRESH_TOKEN</code>.
     * Refresh Token
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, String> REFRESH_TOKEN = createField(DSL.name("REFRESH_TOKEN"), SQLDataType.VARCHAR(512), this, "Refresh Token");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.EXPIRES_AT</code>.
     * Token expiration time
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, LocalDateTime> EXPIRES_AT = createField(DSL.name("EXPIRES_AT"), SQLDataType.LOCALDATETIME(0), this, "Token expiration time");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.TOKEN_METADATA</code>.
     * Token metadata
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, JSON> TOKEN_METADATA = createField(DSL.name("TOKEN_METADATA"), SQLDataType.JSON, this, "Token metadata");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.USERNAME</code>.
     * Username
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, String> USERNAME = createField(DSL.name("USERNAME"), SQLDataType.VARCHAR(512), this, "Username");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.USER_METADATA</code>.
     * User metadata
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, JSON> USER_METADATA = createField(DSL.name("USER_METADATA"), SQLDataType.JSON, this, "User metadata");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.CREATED_AT</code>.
     * Time when this row is created
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is created");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.CREATED_BY</code>. ID
     * of the user who created this row
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, ULong> CREATED_BY = createField(DSL.name("CREATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who created this row");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.UPDATED_BY</code>. ID
     * of the user who updated this row
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, ULong> UPDATED_BY = createField(DSL.name("UPDATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who updated this row");

    /**
     * The column
     * <code>security.security_app_reg_integration_tokens.UPDATED_AT</code>.
     * Time when this row is updated
     */
    public final TableField<SecurityAppRegIntegrationTokensRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("UPDATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is updated");

    private SecurityAppRegIntegrationTokens(Name alias, Table<SecurityAppRegIntegrationTokensRecord> aliased) {
        this(alias, aliased, null);
    }

    private SecurityAppRegIntegrationTokens(Name alias, Table<SecurityAppRegIntegrationTokensRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased
     * <code>security.security_app_reg_integration_tokens</code> table reference
     */
    public SecurityAppRegIntegrationTokens(String alias) {
        this(DSL.name(alias), SECURITY_APP_REG_INTEGRATION_TOKENS);
    }

    /**
     * Create an aliased
     * <code>security.security_app_reg_integration_tokens</code> table reference
     */
    public SecurityAppRegIntegrationTokens(Name alias) {
        this(alias, SECURITY_APP_REG_INTEGRATION_TOKENS);
    }

    /**
     * Create a <code>security.security_app_reg_integration_tokens</code> table
     * reference
     */
    public SecurityAppRegIntegrationTokens() {
        this(DSL.name("security_app_reg_integration_tokens"), null);
    }

    public <O extends Record> SecurityAppRegIntegrationTokens(Table<O> child, ForeignKey<O, SecurityAppRegIntegrationTokensRecord> key) {
        super(child, key, SECURITY_APP_REG_INTEGRATION_TOKENS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Security.SECURITY;
    }

    @Override
    public Identity<SecurityAppRegIntegrationTokensRecord, ULong> getIdentity() {
        return (Identity<SecurityAppRegIntegrationTokensRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<SecurityAppRegIntegrationTokensRecord> getPrimaryKey() {
        return Keys.KEY_SECURITY_APP_REG_INTEGRATION_TOKENS_PRIMARY;
    }

    @Override
    public List<UniqueKey<SecurityAppRegIntegrationTokensRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_SECURITY_APP_REG_INTEGRATION_TOKENS_INTEGRATION_ID);
    }

    @Override
    public List<ForeignKey<SecurityAppRegIntegrationTokensRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK1_INTEGRATION_TOKEN_APP_REG_INTEGRATION_ID);
    }

    private transient SecurityAppRegIntegration _securityAppRegIntegration;

    /**
     * Get the implicit join path to the
     * <code>security.security_app_reg_integration</code> table.
     */
    public SecurityAppRegIntegration securityAppRegIntegration() {
        if (_securityAppRegIntegration == null)
            _securityAppRegIntegration = new SecurityAppRegIntegration(this, Keys.FK1_INTEGRATION_TOKEN_APP_REG_INTEGRATION_ID);

        return _securityAppRegIntegration;
    }

    @Override
    public SecurityAppRegIntegrationTokens as(String alias) {
        return new SecurityAppRegIntegrationTokens(DSL.name(alias), this);
    }

    @Override
    public SecurityAppRegIntegrationTokens as(Name alias) {
        return new SecurityAppRegIntegrationTokens(alias, this);
    }

    @Override
    public SecurityAppRegIntegrationTokens as(Table<?> alias) {
        return new SecurityAppRegIntegrationTokens(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityAppRegIntegrationTokens rename(String name) {
        return new SecurityAppRegIntegrationTokens(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityAppRegIntegrationTokens rename(Name name) {
        return new SecurityAppRegIntegrationTokens(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityAppRegIntegrationTokens rename(Table<?> name) {
        return new SecurityAppRegIntegrationTokens(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<ULong, ULong, String, String, String, String, LocalDateTime, JSON, String, JSON, LocalDateTime, ULong, ULong, LocalDateTime> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function14<? super ULong, ? super ULong, ? super String, ? super String, ? super String, ? super String, ? super LocalDateTime, ? super JSON, ? super String, ? super JSON, ? super LocalDateTime, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function14<? super ULong, ? super ULong, ? super String, ? super String, ? super String, ? super String, ? super LocalDateTime, ? super JSON, ? super String, ? super JSON, ? super LocalDateTime, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}