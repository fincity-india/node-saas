/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.tables.records.SecurityAppRegIntegrationRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
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
public class SecurityAppRegIntegration extends TableImpl<SecurityAppRegIntegrationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>security.security_app_reg_integration</code>
     */
    public static final SecurityAppRegIntegration SECURITY_APP_REG_INTEGRATION = new SecurityAppRegIntegration();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecurityAppRegIntegrationRecord> getRecordType() {
        return SecurityAppRegIntegrationRecord.class;
    }

    /**
     * The column <code>security.security_app_reg_integration.ID</code>. Primary
     * key
     */
    public final TableField<SecurityAppRegIntegrationRecord, ULong> ID = createField(DSL.name("ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key");

    /**
     * The column <code>security.security_app_reg_integration.APP_ID</code>. App
     * ID
     */
    public final TableField<SecurityAppRegIntegrationRecord, ULong> APP_ID = createField(DSL.name("APP_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "App ID");

    /**
     * The column <code>security.security_app_reg_integration.CLIENT_ID</code>.
     * Client ID
     */
    public final TableField<SecurityAppRegIntegrationRecord, ULong> CLIENT_ID = createField(DSL.name("CLIENT_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Client ID");

    /**
     * The column
     * <code>security.security_app_reg_integration.INTEGRATION_ID</code>.
     * Integration ID
     */
    public final TableField<SecurityAppRegIntegrationRecord, ULong> INTEGRATION_ID = createField(DSL.name("INTEGRATION_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Integration ID");

    /**
     * The column <code>security.security_app_reg_integration.CREATED_BY</code>.
     * ID of the user who created this row
     */
    public final TableField<SecurityAppRegIntegrationRecord, ULong> CREATED_BY = createField(DSL.name("CREATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who created this row");

    /**
     * The column <code>security.security_app_reg_integration.CREATED_AT</code>.
     * Time when this row is created
     */
    public final TableField<SecurityAppRegIntegrationRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is created");

    private SecurityAppRegIntegration(Name alias, Table<SecurityAppRegIntegrationRecord> aliased) {
        this(alias, aliased, null);
    }

    private SecurityAppRegIntegration(Name alias, Table<SecurityAppRegIntegrationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>security.security_app_reg_integration</code>
     * table reference
     */
    public SecurityAppRegIntegration(String alias) {
        this(DSL.name(alias), SECURITY_APP_REG_INTEGRATION);
    }

    /**
     * Create an aliased <code>security.security_app_reg_integration</code>
     * table reference
     */
    public SecurityAppRegIntegration(Name alias) {
        this(alias, SECURITY_APP_REG_INTEGRATION);
    }

    /**
     * Create a <code>security.security_app_reg_integration</code> table
     * reference
     */
    public SecurityAppRegIntegration() {
        this(DSL.name("security_app_reg_integration"), null);
    }

    public <O extends Record> SecurityAppRegIntegration(Table<O> child, ForeignKey<O, SecurityAppRegIntegrationRecord> key) {
        super(child, key, SECURITY_APP_REG_INTEGRATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Security.SECURITY;
    }

    @Override
    public Identity<SecurityAppRegIntegrationRecord, ULong> getIdentity() {
        return (Identity<SecurityAppRegIntegrationRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<SecurityAppRegIntegrationRecord> getPrimaryKey() {
        return Keys.KEY_SECURITY_APP_REG_INTEGRATION_PRIMARY;
    }

    @Override
    public List<UniqueKey<SecurityAppRegIntegrationRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_SECURITY_APP_REG_INTEGRATION_APP_ID);
    }

    @Override
    public List<ForeignKey<SecurityAppRegIntegrationRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK1_APP_REG_INTEGRATION_APP_ID, Keys.FK3_APP_REG_INTEGRATION_CLIENT_ID, Keys.FK2_APP_REG_INTEGRATION_INTEGRATION_ID);
    }

    private transient SecurityApp _securityApp;
    private transient SecurityClient _securityClient;
    private transient SecurityIntegration _securityIntegration;

    /**
     * Get the implicit join path to the <code>security.security_app</code>
     * table.
     */
    public SecurityApp securityApp() {
        if (_securityApp == null)
            _securityApp = new SecurityApp(this, Keys.FK1_APP_REG_INTEGRATION_APP_ID);

        return _securityApp;
    }

    /**
     * Get the implicit join path to the <code>security.security_client</code>
     * table.
     */
    public SecurityClient securityClient() {
        if (_securityClient == null)
            _securityClient = new SecurityClient(this, Keys.FK3_APP_REG_INTEGRATION_CLIENT_ID);

        return _securityClient;
    }

    /**
     * Get the implicit join path to the
     * <code>security.security_integration</code> table.
     */
    public SecurityIntegration securityIntegration() {
        if (_securityIntegration == null)
            _securityIntegration = new SecurityIntegration(this, Keys.FK2_APP_REG_INTEGRATION_INTEGRATION_ID);

        return _securityIntegration;
    }

    @Override
    public SecurityAppRegIntegration as(String alias) {
        return new SecurityAppRegIntegration(DSL.name(alias), this);
    }

    @Override
    public SecurityAppRegIntegration as(Name alias) {
        return new SecurityAppRegIntegration(alias, this);
    }

    @Override
    public SecurityAppRegIntegration as(Table<?> alias) {
        return new SecurityAppRegIntegration(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityAppRegIntegration rename(String name) {
        return new SecurityAppRegIntegration(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityAppRegIntegration rename(Name name) {
        return new SecurityAppRegIntegration(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityAppRegIntegration rename(Table<?> name) {
        return new SecurityAppRegIntegration(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<ULong, ULong, ULong, ULong, ULong, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
