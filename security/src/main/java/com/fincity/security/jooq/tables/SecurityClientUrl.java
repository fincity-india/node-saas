/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.tables.SecurityApp.SecurityAppPath;
import com.fincity.security.jooq.tables.SecurityClient.SecurityClientPath;
import com.fincity.security.jooq.tables.SecuritySslCertificate.SecuritySslCertificatePath;
import com.fincity.security.jooq.tables.SecuritySslRequest.SecuritySslRequestPath;
import com.fincity.security.jooq.tables.records.SecurityClientUrlRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
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
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
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
     * Pattern to identify user's Client ID
     */
    public final TableField<SecurityClientUrlRecord, String> URL_PATTERN = createField(DSL.name("URL_PATTERN"), SQLDataType.VARCHAR(512).nullable(false), this, "URL Pattern to identify user's Client ID");

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
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SecurityClientUrl(Name alias, Table<SecurityClientUrlRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
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

    public <O extends Record> SecurityClientUrl(Table<O> path, ForeignKey<O, SecurityClientUrlRecord> childPath, InverseForeignKey<O, SecurityClientUrlRecord> parentPath) {
        super(path, childPath, parentPath, SECURITY_CLIENT_URL);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SecurityClientUrlPath extends SecurityClientUrl implements Path<SecurityClientUrlRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SecurityClientUrlPath(Table<O> path, ForeignKey<O, SecurityClientUrlRecord> childPath, InverseForeignKey<O, SecurityClientUrlRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SecurityClientUrlPath(Name alias, Table<SecurityClientUrlRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SecurityClientUrlPath as(String alias) {
            return new SecurityClientUrlPath(DSL.name(alias), this);
        }

        @Override
        public SecurityClientUrlPath as(Name alias) {
            return new SecurityClientUrlPath(alias, this);
        }

        @Override
        public SecurityClientUrlPath as(Table<?> alias) {
            return new SecurityClientUrlPath(alias.getQualifiedName(), this);
        }
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
        return Arrays.asList(Keys.FK1_CLIENT_URL_APP_CODE, Keys.FK1_CLIENT_URL_CLIENT_ID);
    }

    private transient SecurityAppPath _securityApp;

    /**
     * Get the implicit join path to the <code>security.security_app</code>
     * table.
     */
    public SecurityAppPath securityApp() {
        if (_securityApp == null)
            _securityApp = new SecurityAppPath(this, Keys.FK1_CLIENT_URL_APP_CODE, null);

        return _securityApp;
    }

    private transient SecurityClientPath _securityClient;

    /**
     * Get the implicit join path to the <code>security.security_client</code>
     * table.
     */
    public SecurityClientPath securityClient() {
        if (_securityClient == null)
            _securityClient = new SecurityClientPath(this, Keys.FK1_CLIENT_URL_CLIENT_ID, null);

        return _securityClient;
    }

    private transient SecuritySslCertificatePath _securitySslCertificate;

    /**
     * Get the implicit to-many join path to the
     * <code>security.security_ssl_certificate</code> table
     */
    public SecuritySslCertificatePath securitySslCertificate() {
        if (_securitySslCertificate == null)
            _securitySslCertificate = new SecuritySslCertificatePath(this, null, Keys.FK1_SSL_CRT_CLNT_URL_ID.getInverseKey());

        return _securitySslCertificate;
    }

    private transient SecuritySslRequestPath _securitySslRequest;

    /**
     * Get the implicit to-many join path to the
     * <code>security.security_ssl_request</code> table
     */
    public SecuritySslRequestPath securitySslRequest() {
        if (_securitySslRequest == null)
            _securitySslRequest = new SecuritySslRequestPath(this, null, Keys.FK1_SSL_REQ_CLNT_URL_ID.getInverseKey());

        return _securitySslRequest;
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

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientUrl where(Condition condition) {
        return new SecurityClientUrl(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientUrl where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientUrl where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientUrl where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientUrl where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientUrl where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientUrl where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientUrl where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientUrl whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientUrl whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
