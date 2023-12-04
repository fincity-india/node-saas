/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.tables.records.SecurityCodeAccessRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
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
public class SecurityCodeAccess extends TableImpl<SecurityCodeAccessRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>security.security_code_access</code>
     */
    public static final SecurityCodeAccess SECURITY_CODE_ACCESS = new SecurityCodeAccess();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecurityCodeAccessRecord> getRecordType() {
        return SecurityCodeAccessRecord.class;
    }

    /**
     * The column <code>security.security_code_access.ID</code>. Primary key
     */
    public final TableField<SecurityCodeAccessRecord, ULong> ID = createField(DSL.name("ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key");

    /**
     * The column <code>security.security_code_access.EMAIL_ID</code>. Email id
     * of the client
     */
    public final TableField<SecurityCodeAccessRecord, String> EMAIL_ID = createField(DSL.name("EMAIL_ID"), SQLDataType.VARCHAR(320).nullable(false), this, "Email id of the client");

    /**
     * The column <code>security.security_code_access.CODE</code>. Unique access
     * code for logging in
     */
    public final TableField<SecurityCodeAccessRecord, String> CODE = createField(DSL.name("CODE"), SQLDataType.CHAR(32).nullable(false), this, "Unique access code for logging in");

    /**
     * The column <code>security.security_code_access.APP_ID</code>. App id to
     * which this user belongs to.
     */
    public final TableField<SecurityCodeAccessRecord, ULong> APP_ID = createField(DSL.name("APP_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "App id to which this user belongs to.");

    /**
     * The column <code>security.security_code_access.CLIENT_ID</code>. Client
     * id to which this user belongs to.
     */
    public final TableField<SecurityCodeAccessRecord, ULong> CLIENT_ID = createField(DSL.name("CLIENT_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Client id to which this user belongs to.");

    /**
     * The column <code>security.security_code_access.CREATED_BY</code>. ID of
     * the user who created this row
     */
    public final TableField<SecurityCodeAccessRecord, ULong> CREATED_BY = createField(DSL.name("CREATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who created this row");

    /**
     * The column <code>security.security_code_access.CREATED_AT</code>. Time
     * when this row is created
     */
    public final TableField<SecurityCodeAccessRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is created");

    private SecurityCodeAccess(Name alias, Table<SecurityCodeAccessRecord> aliased) {
        this(alias, aliased, null);
    }

    private SecurityCodeAccess(Name alias, Table<SecurityCodeAccessRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>security.security_code_access</code> table
     * reference
     */
    public SecurityCodeAccess(String alias) {
        this(DSL.name(alias), SECURITY_CODE_ACCESS);
    }

    /**
     * Create an aliased <code>security.security_code_access</code> table
     * reference
     */
    public SecurityCodeAccess(Name alias) {
        this(alias, SECURITY_CODE_ACCESS);
    }

    /**
     * Create a <code>security.security_code_access</code> table reference
     */
    public SecurityCodeAccess() {
        this(DSL.name("security_code_access"), null);
    }

    public <O extends Record> SecurityCodeAccess(Table<O> child, ForeignKey<O, SecurityCodeAccessRecord> key) {
        super(child, key, SECURITY_CODE_ACCESS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Security.SECURITY;
    }

    @Override
    public Identity<SecurityCodeAccessRecord, ULong> getIdentity() {
        return (Identity<SecurityCodeAccessRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<SecurityCodeAccessRecord> getPrimaryKey() {
        return Keys.KEY_SECURITY_CODE_ACCESS_PRIMARY;
    }

    @Override
    public List<UniqueKey<SecurityCodeAccessRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_SECURITY_CODE_ACCESS_UK1_CODE_ACCESS_EMAIL_APP_CLIENT, Keys.KEY_SECURITY_CODE_ACCESS_UK1_CODE_ACCESS_CODE);
    }

    @Override
    public List<ForeignKey<SecurityCodeAccessRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK2_CODE_APP_ID, Keys.FK1_CODE_CLIENT_ID);
    }

    private transient SecurityApp _securityApp;
    private transient SecurityClient _securityClient;

    /**
     * Get the implicit join path to the <code>security.security_app</code>
     * table.
     */
    public SecurityApp securityApp() {
        if (_securityApp == null)
            _securityApp = new SecurityApp(this, Keys.FK2_CODE_APP_ID);

        return _securityApp;
    }

    /**
     * Get the implicit join path to the <code>security.security_client</code>
     * table.
     */
    public SecurityClient securityClient() {
        if (_securityClient == null)
            _securityClient = new SecurityClient(this, Keys.FK1_CODE_CLIENT_ID);

        return _securityClient;
    }

    @Override
    public SecurityCodeAccess as(String alias) {
        return new SecurityCodeAccess(DSL.name(alias), this);
    }

    @Override
    public SecurityCodeAccess as(Name alias) {
        return new SecurityCodeAccess(alias, this);
    }

    @Override
    public SecurityCodeAccess as(Table<?> alias) {
        return new SecurityCodeAccess(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityCodeAccess rename(String name) {
        return new SecurityCodeAccess(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityCodeAccess rename(Name name) {
        return new SecurityCodeAccess(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityCodeAccess rename(Table<?> name) {
        return new SecurityCodeAccess(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<ULong, String, String, ULong, ULong, ULong, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super ULong, ? super String, ? super String, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super ULong, ? super String, ? super String, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
