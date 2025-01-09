/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.enums.SecurityClientOtpPolicyTargetType;
import com.fincity.security.jooq.tables.SecurityApp.SecurityAppPath;
import com.fincity.security.jooq.tables.SecurityClient.SecurityClientPath;
import com.fincity.security.jooq.tables.records.SecurityClientOtpPolicyRecord;

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
import org.jooq.types.UShort;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SecurityClientOtpPolicy extends TableImpl<SecurityClientOtpPolicyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>security.security_client_otp_policy</code>
     */
    public static final SecurityClientOtpPolicy SECURITY_CLIENT_OTP_POLICY = new SecurityClientOtpPolicy();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecurityClientOtpPolicyRecord> getRecordType() {
        return SecurityClientOtpPolicyRecord.class;
    }

    /**
     * The column <code>security.security_client_otp_policy.ID</code>. Primary
     * key, unique identifier for each OTP policy entry
     */
    public final TableField<SecurityClientOtpPolicyRecord, ULong> ID = createField(DSL.name("ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key, unique identifier for each OTP policy entry");

    /**
     * The column <code>security.security_client_otp_policy.CLIENT_ID</code>.
     * Identifier for the client to which this OTP policy belongs. References
     * security_client table
     */
    public final TableField<SecurityClientOtpPolicyRecord, ULong> CLIENT_ID = createField(DSL.name("CLIENT_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Identifier for the client to which this OTP policy belongs. References security_client table");

    /**
     * The column <code>security.security_client_otp_policy.APP_ID</code>.
     * Identifier for the application to which this OTP policy belongs.
     * References security_app table
     */
    public final TableField<SecurityClientOtpPolicyRecord, ULong> APP_ID = createField(DSL.name("APP_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Identifier for the application to which this OTP policy belongs. References security_app table");

    /**
     * The column <code>security.security_client_otp_policy.TARGET_TYPE</code>.
     * The target medium for the OTP delivery: EMAIL, PHONE, or BOTH
     */
    public final TableField<SecurityClientOtpPolicyRecord, SecurityClientOtpPolicyTargetType> TARGET_TYPE = createField(DSL.name("TARGET_TYPE"), SQLDataType.VARCHAR(5).nullable(false).defaultValue(DSL.inline("EMAIL", SQLDataType.VARCHAR)).asEnumDataType(SecurityClientOtpPolicyTargetType.class), this, "The target medium for the OTP delivery: EMAIL, PHONE, or BOTH");

    /**
     * The column <code>security.security_client_otp_policy.IS_CONSTANT</code>.
     * Flag indicating if OTP should be a constant value
     */
    public final TableField<SecurityClientOtpPolicyRecord, Byte> IS_CONSTANT = createField(DSL.name("IS_CONSTANT"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "Flag indicating if OTP should be a constant value");

    /**
     * The column
     * <code>security.security_client_otp_policy.CONSTANT_VALUE</code>. Value of
     * OTP if IS_CONSTANT is true
     */
    public final TableField<SecurityClientOtpPolicyRecord, String> CONSTANT_VALUE = createField(DSL.name("CONSTANT_VALUE"), SQLDataType.CHAR(10), this, "Value of OTP if IS_CONSTANT is true");

    /**
     * The column <code>security.security_client_otp_policy.IS_NUMERIC</code>.
     * Flag indicating if OTP should contain only numeric characters
     */
    public final TableField<SecurityClientOtpPolicyRecord, Byte> IS_NUMERIC = createField(DSL.name("IS_NUMERIC"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("1", SQLDataType.TINYINT)), this, "Flag indicating if OTP should contain only numeric characters");

    /**
     * The column
     * <code>security.security_client_otp_policy.IS_ALPHANUMERIC</code>. Flag
     * indicating if OTP should contain alphanumeric characters
     */
    public final TableField<SecurityClientOtpPolicyRecord, Byte> IS_ALPHANUMERIC = createField(DSL.name("IS_ALPHANUMERIC"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "Flag indicating if OTP should contain alphanumeric characters");

    /**
     * The column <code>security.security_client_otp_policy.LENGTH</code>.
     * Length of the OTP to be generated
     */
    public final TableField<SecurityClientOtpPolicyRecord, UShort> LENGTH = createField(DSL.name("LENGTH"), SQLDataType.SMALLINTUNSIGNED.nullable(false).defaultValue(DSL.inline("4", SQLDataType.SMALLINTUNSIGNED)), this, "Length of the OTP to be generated");

    /**
     * The column
     * <code>security.security_client_otp_policy.RESEND_SAME_OTP</code>. Flag
     * indication weather to send same OTP in resend request.
     */
    public final TableField<SecurityClientOtpPolicyRecord, Byte> RESEND_SAME_OTP = createField(DSL.name("RESEND_SAME_OTP"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "Flag indication weather to send same OTP in resend request.");

    /**
     * The column
     * <code>security.security_client_otp_policy.NO_RESEND_ATTEMPTS</code>.
     * Maximum number of Resend attempts allowed before User is blocked
     */
    public final TableField<SecurityClientOtpPolicyRecord, UShort> NO_RESEND_ATTEMPTS = createField(DSL.name("NO_RESEND_ATTEMPTS"), SQLDataType.SMALLINTUNSIGNED.nullable(false).defaultValue(DSL.inline("3", SQLDataType.SMALLINTUNSIGNED)), this, "Maximum number of Resend attempts allowed before User is blocked");

    /**
     * The column
     * <code>security.security_client_otp_policy.EXPIRE_INTERVAL</code>. Time
     * interval in minutes after which OTP will expire
     */
    public final TableField<SecurityClientOtpPolicyRecord, ULong> EXPIRE_INTERVAL = createField(DSL.name("EXPIRE_INTERVAL"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("5", SQLDataType.BIGINTUNSIGNED)), this, "Time interval in minutes after which OTP will expire");

    /**
     * The column
     * <code>security.security_client_otp_policy.NO_FAILED_ATTEMPTS</code>.
     * Maximum number of failed attempts allowed before OTP is invalidated
     */
    public final TableField<SecurityClientOtpPolicyRecord, UShort> NO_FAILED_ATTEMPTS = createField(DSL.name("NO_FAILED_ATTEMPTS"), SQLDataType.SMALLINTUNSIGNED.nullable(false).defaultValue(DSL.inline("3", SQLDataType.SMALLINTUNSIGNED)), this, "Maximum number of failed attempts allowed before OTP is invalidated");

    /**
     * The column
     * <code>security.security_client_otp_policy.USER_LOCK_TIME</code>. Time in
     * minutes for which user need to be locked it policy violates
     */
    public final TableField<SecurityClientOtpPolicyRecord, ULong> USER_LOCK_TIME = createField(DSL.name("USER_LOCK_TIME"), SQLDataType.BIGINTUNSIGNED.nullable(false).defaultValue(DSL.inline("30", SQLDataType.BIGINTUNSIGNED)), this, "Time in minutes for which user need to be locked it policy violates");

    /**
     * The column <code>security.security_client_otp_policy.CREATED_BY</code>.
     * ID of the user who created this row
     */
    public final TableField<SecurityClientOtpPolicyRecord, ULong> CREATED_BY = createField(DSL.name("CREATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who created this row");

    /**
     * The column <code>security.security_client_otp_policy.CREATED_AT</code>.
     * Time when this row is created
     */
    public final TableField<SecurityClientOtpPolicyRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is created");

    /**
     * The column <code>security.security_client_otp_policy.UPDATED_BY</code>.
     * ID of the user who last updated this row
     */
    public final TableField<SecurityClientOtpPolicyRecord, ULong> UPDATED_BY = createField(DSL.name("UPDATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who last updated this row");

    /**
     * The column <code>security.security_client_otp_policy.UPDATED_AT</code>.
     * Time when this row is last updated
     */
    public final TableField<SecurityClientOtpPolicyRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("UPDATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is last updated");

    private SecurityClientOtpPolicy(Name alias, Table<SecurityClientOtpPolicyRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SecurityClientOtpPolicy(Name alias, Table<SecurityClientOtpPolicyRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>security.security_client_otp_policy</code> table
     * reference
     */
    public SecurityClientOtpPolicy(String alias) {
        this(DSL.name(alias), SECURITY_CLIENT_OTP_POLICY);
    }

    /**
     * Create an aliased <code>security.security_client_otp_policy</code> table
     * reference
     */
    public SecurityClientOtpPolicy(Name alias) {
        this(alias, SECURITY_CLIENT_OTP_POLICY);
    }

    /**
     * Create a <code>security.security_client_otp_policy</code> table reference
     */
    public SecurityClientOtpPolicy() {
        this(DSL.name("security_client_otp_policy"), null);
    }

    public <O extends Record> SecurityClientOtpPolicy(Table<O> path, ForeignKey<O, SecurityClientOtpPolicyRecord> childPath, InverseForeignKey<O, SecurityClientOtpPolicyRecord> parentPath) {
        super(path, childPath, parentPath, SECURITY_CLIENT_OTP_POLICY);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SecurityClientOtpPolicyPath extends SecurityClientOtpPolicy implements Path<SecurityClientOtpPolicyRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SecurityClientOtpPolicyPath(Table<O> path, ForeignKey<O, SecurityClientOtpPolicyRecord> childPath, InverseForeignKey<O, SecurityClientOtpPolicyRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SecurityClientOtpPolicyPath(Name alias, Table<SecurityClientOtpPolicyRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SecurityClientOtpPolicyPath as(String alias) {
            return new SecurityClientOtpPolicyPath(DSL.name(alias), this);
        }

        @Override
        public SecurityClientOtpPolicyPath as(Name alias) {
            return new SecurityClientOtpPolicyPath(alias, this);
        }

        @Override
        public SecurityClientOtpPolicyPath as(Table<?> alias) {
            return new SecurityClientOtpPolicyPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Security.SECURITY;
    }

    @Override
    public Identity<SecurityClientOtpPolicyRecord, ULong> getIdentity() {
        return (Identity<SecurityClientOtpPolicyRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<SecurityClientOtpPolicyRecord> getPrimaryKey() {
        return Keys.KEY_SECURITY_CLIENT_OTP_POLICY_PRIMARY;
    }

    @Override
    public List<UniqueKey<SecurityClientOtpPolicyRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_SECURITY_CLIENT_OTP_POLICY_UK1_CLIENT_OTP_POL_CLIENT_ID_APP_ID);
    }

    @Override
    public List<ForeignKey<SecurityClientOtpPolicyRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK1_CLIENT_OTP_POL_CLIENT_ID, Keys.FK2_CLIENT_OTP_POL_APP_ID);
    }

    private transient SecurityClientPath _securityClient;

    /**
     * Get the implicit join path to the <code>security.security_client</code>
     * table.
     */
    public SecurityClientPath securityClient() {
        if (_securityClient == null)
            _securityClient = new SecurityClientPath(this, Keys.FK1_CLIENT_OTP_POL_CLIENT_ID, null);

        return _securityClient;
    }

    private transient SecurityAppPath _securityApp;

    /**
     * Get the implicit join path to the <code>security.security_app</code>
     * table.
     */
    public SecurityAppPath securityApp() {
        if (_securityApp == null)
            _securityApp = new SecurityAppPath(this, Keys.FK2_CLIENT_OTP_POL_APP_ID, null);

        return _securityApp;
    }

    @Override
    public SecurityClientOtpPolicy as(String alias) {
        return new SecurityClientOtpPolicy(DSL.name(alias), this);
    }

    @Override
    public SecurityClientOtpPolicy as(Name alias) {
        return new SecurityClientOtpPolicy(alias, this);
    }

    @Override
    public SecurityClientOtpPolicy as(Table<?> alias) {
        return new SecurityClientOtpPolicy(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientOtpPolicy rename(String name) {
        return new SecurityClientOtpPolicy(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientOtpPolicy rename(Name name) {
        return new SecurityClientOtpPolicy(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientOtpPolicy rename(Table<?> name) {
        return new SecurityClientOtpPolicy(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientOtpPolicy where(Condition condition) {
        return new SecurityClientOtpPolicy(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientOtpPolicy where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientOtpPolicy where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientOtpPolicy where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientOtpPolicy where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientOtpPolicy where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientOtpPolicy where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientOtpPolicy where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientOtpPolicy whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientOtpPolicy whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
