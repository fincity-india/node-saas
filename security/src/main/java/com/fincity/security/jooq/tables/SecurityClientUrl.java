/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.tables.records.SecurityClientUrlRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function8;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row8;
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
public class SecurityClientUrl extends TableImpl<SecurityClientUrlRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>security.security_client_url</code>
     */
    public static final SecurityClientUrl SECURITY_CLIENT_URL = new SecurityClientUrl();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecurityClientUrlRecord> getRecordType() {
        return SecurityClientUrlRecord.class;
    }

    /**
     * The column <code>security.security_client_url.ID</code>. Primary key
     */
    public final TableField<SecurityClientUrlRecord, ULong> ID = createField(DSL.name("ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key");

    /**
     * The column <code>security.security_client_url.CLIENT_ID</code>. Client ID
     */
    public final TableField<SecurityClientUrlRecord, ULong> CLIENT_ID = createField(DSL.name("CLIENT_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Client ID");

    /**
     * The column <code>security.security_client_url.URL_PATTERN</code>. URL
     * Pattern to identify users Client ID
     */
    public final TableField<SecurityClientUrlRecord, String> URL_PATTERN = createField(DSL.name("URL_PATTERN"), SQLDataType.VARCHAR(512).nullable(false), this, "URL Pattern to identify users Client ID");

    /**
     * The column <code>security.security_client_url.APP_CODE</code>.
     */
    public final TableField<SecurityClientUrlRecord, String> APP_CODE = createField(DSL.name("APP_CODE"), SQLDataType.CHAR(64).nullable(false), this, "");

    /**
     * The column <code>security.security_client_url.CREATED_BY</code>. ID of
     * the user who created this row
     */
    public final TableField<SecurityClientUrlRecord, ULong> CREATED_BY = createField(DSL.name("CREATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who created this row");

    /**
     * The column <code>security.security_client_url.CREATED_AT</code>. Time
     * when this row is created
     */
    public final TableField<SecurityClientUrlRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is created");

    /**
     * The column <code>security.security_client_url.UPDATED_BY</code>. ID of
     * the user who updated this row
     */
    public final TableField<SecurityClientUrlRecord, ULong> UPDATED_BY = createField(DSL.name("UPDATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who updated this row");

    /**
     * The column <code>security.security_client_url.UPDATED_AT</code>. Time
     * when this row is updated
     */
    public final TableField<SecurityClientUrlRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("UPDATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is updated");

    private SecurityClientUrl(Name alias, Table<SecurityClientUrlRecord> aliased) {
        this(alias, aliased, null);
    }

    private SecurityClientUrl(Name alias, Table<SecurityClientUrlRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>security.security_client_url</code> table
     * reference
     */
    public SecurityClientUrl(String alias) {
        this(DSL.name(alias), SECURITY_CLIENT_URL);
    }

    /**
     * Create an aliased <code>security.security_client_url</code> table
     * reference
     */
    public SecurityClientUrl(Name alias) {
        this(alias, SECURITY_CLIENT_URL);
    }

    /**
     * Create a <code>security.security_client_url</code> table reference
     */
    public SecurityClientUrl() {
        this(DSL.name("security_client_url"), null);
    }

    public <O extends Record> SecurityClientUrl(Table<O> child, ForeignKey<O, SecurityClientUrlRecord> key) {
        super(child, key, SECURITY_CLIENT_URL);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Security.SECURITY;
    }

    @Override
    public Identity<SecurityClientUrlRecord, ULong> getIdentity() {
        return (Identity<SecurityClientUrlRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<SecurityClientUrlRecord> getPrimaryKey() {
        return Keys.KEY_SECURITY_CLIENT_URL_PRIMARY;
    }

    @Override
    public List<UniqueKey<SecurityClientUrlRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_SECURITY_CLIENT_URL_UK1_URL_PATTERN);
    }

    @Override
    public List<ForeignKey<SecurityClientUrlRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK1_CLIENT_URL_CLIENT_ID, Keys.FK1_CLIENT_URL_APP_CODE);
    }

    private transient SecurityClient _securityClient;
    private transient SecurityApp _securityApp;

    /**
     * Get the implicit join path to the <code>security.security_client</code>
     * table.
     */
    public SecurityClient securityClient() {
        if (_securityClient == null)
            _securityClient = new SecurityClient(this, Keys.FK1_CLIENT_URL_CLIENT_ID);

        return _securityClient;
    }

    /**
     * Get the implicit join path to the <code>security.security_app</code>
     * table.
     */
    public SecurityApp securityApp() {
        if (_securityApp == null)
            _securityApp = new SecurityApp(this, Keys.FK1_CLIENT_URL_APP_CODE);

        return _securityApp;
    }

    @Override
    public SecurityClientUrl as(String alias) {
        return new SecurityClientUrl(DSL.name(alias), this);
    }

    @Override
    public SecurityClientUrl as(Name alias) {
        return new SecurityClientUrl(alias, this);
    }

    @Override
    public SecurityClientUrl as(Table<?> alias) {
        return new SecurityClientUrl(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientUrl rename(String name) {
        return new SecurityClientUrl(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientUrl rename(Name name) {
        return new SecurityClientUrl(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientUrl rename(Table<?> name) {
        return new SecurityClientUrl(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<ULong, ULong, String, String, ULong, LocalDateTime, ULong, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super ULong, ? super ULong, ? super String, ? super String, ? super ULong, ? super LocalDateTime, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super ULong, ? super ULong, ? super String, ? super String, ? super ULong, ? super LocalDateTime, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
