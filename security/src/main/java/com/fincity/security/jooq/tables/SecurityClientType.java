/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.tables.SecurityAppRegAccess.SecurityAppRegAccessPath;
import com.fincity.security.jooq.tables.SecurityAppRegFileAccess.SecurityAppRegFileAccessPath;
import com.fincity.security.jooq.tables.SecurityAppRegPackage.SecurityAppRegPackagePath;
import com.fincity.security.jooq.tables.SecurityAppRegUserRole.SecurityAppRegUserRolePath;
import com.fincity.security.jooq.tables.SecurityClient.SecurityClientPath;
import com.fincity.security.jooq.tables.records.SecurityClientTypeRecord;

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
public class SecurityClientType extends TableImpl<SecurityClientTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>security.security_client_type</code>
     */
    public static final SecurityClientType SECURITY_CLIENT_TYPE = new SecurityClientType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecurityClientTypeRecord> getRecordType() {
        return SecurityClientTypeRecord.class;
    }

    /**
     * The column <code>security.security_client_type.ID</code>. Primary key
     */
    public final TableField<SecurityClientTypeRecord, ULong> ID = createField(DSL.name("ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key");

    /**
     * The column <code>security.security_client_type.CODE</code>. Code
     */
    public final TableField<SecurityClientTypeRecord, String> CODE = createField(DSL.name("CODE"), SQLDataType.CHAR(4).nullable(false), this, "Code");

    /**
     * The column <code>security.security_client_type.TYPE</code>. Type
     */
    public final TableField<SecurityClientTypeRecord, String> TYPE = createField(DSL.name("TYPE"), SQLDataType.VARCHAR(256).nullable(false), this, "Type");

    /**
     * The column <code>security.security_client_type.DESCRIPTION</code>.
     * Description of the client type
     */
    public final TableField<SecurityClientTypeRecord, String> DESCRIPTION = createField(DSL.name("DESCRIPTION"), SQLDataType.CLOB, this, "Description of the client type");

    /**
     * The column <code>security.security_client_type.CREATED_BY</code>. ID of
     * the user who created this row
     */
    public final TableField<SecurityClientTypeRecord, ULong> CREATED_BY = createField(DSL.name("CREATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who created this row");

    /**
     * The column <code>security.security_client_type.CREATED_AT</code>. Time
     * when this row is created
     */
    public final TableField<SecurityClientTypeRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is created");

    /**
     * The column <code>security.security_client_type.UPDATED_BY</code>. ID of
     * the user who updated this row
     */
    public final TableField<SecurityClientTypeRecord, ULong> UPDATED_BY = createField(DSL.name("UPDATED_BY"), SQLDataType.BIGINTUNSIGNED, this, "ID of the user who updated this row");

    /**
     * The column <code>security.security_client_type.UPDATED_AT</code>. Time
     * when this row is updated
     */
    public final TableField<SecurityClientTypeRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("UPDATED_AT"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Time when this row is updated");

    private SecurityClientType(Name alias, Table<SecurityClientTypeRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SecurityClientType(Name alias, Table<SecurityClientTypeRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>security.security_client_type</code> table
     * reference
     */
    public SecurityClientType(String alias) {
        this(DSL.name(alias), SECURITY_CLIENT_TYPE);
    }

    /**
     * Create an aliased <code>security.security_client_type</code> table
     * reference
     */
    public SecurityClientType(Name alias) {
        this(alias, SECURITY_CLIENT_TYPE);
    }

    /**
     * Create a <code>security.security_client_type</code> table reference
     */
    public SecurityClientType() {
        this(DSL.name("security_client_type"), null);
    }

    public <O extends Record> SecurityClientType(Table<O> path, ForeignKey<O, SecurityClientTypeRecord> childPath, InverseForeignKey<O, SecurityClientTypeRecord> parentPath) {
        super(path, childPath, parentPath, SECURITY_CLIENT_TYPE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SecurityClientTypePath extends SecurityClientType implements Path<SecurityClientTypeRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SecurityClientTypePath(Table<O> path, ForeignKey<O, SecurityClientTypeRecord> childPath, InverseForeignKey<O, SecurityClientTypeRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SecurityClientTypePath(Name alias, Table<SecurityClientTypeRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SecurityClientTypePath as(String alias) {
            return new SecurityClientTypePath(DSL.name(alias), this);
        }

        @Override
        public SecurityClientTypePath as(Name alias) {
            return new SecurityClientTypePath(alias, this);
        }

        @Override
        public SecurityClientTypePath as(Table<?> alias) {
            return new SecurityClientTypePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Security.SECURITY;
    }

    @Override
    public Identity<SecurityClientTypeRecord, ULong> getIdentity() {
        return (Identity<SecurityClientTypeRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<SecurityClientTypeRecord> getPrimaryKey() {
        return Keys.KEY_SECURITY_CLIENT_TYPE_PRIMARY;
    }

    @Override
    public List<UniqueKey<SecurityClientTypeRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_SECURITY_CLIENT_TYPE_UK1_CLIENT_TYPE_CODE);
    }

    private transient SecurityClientPath _securityClient;

    /**
     * Get the implicit to-many join path to the
     * <code>security.security_client</code> table
     */
    public SecurityClientPath securityClient() {
        if (_securityClient == null)
            _securityClient = new SecurityClientPath(this, null, Keys.FK1_CLIENT_CLIENT_TYPE_CODE.getInverseKey());

        return _securityClient;
    }

    private transient SecurityAppRegAccessPath _securityAppRegAccess;

    /**
     * Get the implicit to-many join path to the
     * <code>security.security_app_reg_access</code> table
     */
    public SecurityAppRegAccessPath securityAppRegAccess() {
        if (_securityAppRegAccess == null)
            _securityAppRegAccess = new SecurityAppRegAccessPath(this, null, Keys.FK4_APP_REG_ACC_CLIENT_TYPE.getInverseKey());

        return _securityAppRegAccess;
    }

    private transient SecurityAppRegFileAccessPath _securityAppRegFileAccess;

    /**
     * Get the implicit to-many join path to the
     * <code>security.security_app_reg_file_access</code> table
     */
    public SecurityAppRegFileAccessPath securityAppRegFileAccess() {
        if (_securityAppRegFileAccess == null)
            _securityAppRegFileAccess = new SecurityAppRegFileAccessPath(this, null, Keys.FK4_APP_REG_FILE_ACC_CLIENT_TYPE.getInverseKey());

        return _securityAppRegFileAccess;
    }

    private transient SecurityAppRegPackagePath _securityAppRegPackage;

    /**
     * Get the implicit to-many join path to the
     * <code>security.security_app_reg_package</code> table
     */
    public SecurityAppRegPackagePath securityAppRegPackage() {
        if (_securityAppRegPackage == null)
            _securityAppRegPackage = new SecurityAppRegPackagePath(this, null, Keys.FK4_APP_REG_PKG_CLIENT_TYPE.getInverseKey());

        return _securityAppRegPackage;
    }

    private transient SecurityAppRegUserRolePath _securityAppRegUserRole;

    /**
     * Get the implicit to-many join path to the
     * <code>security.security_app_reg_user_role</code> table
     */
    public SecurityAppRegUserRolePath securityAppRegUserRole() {
        if (_securityAppRegUserRole == null)
            _securityAppRegUserRole = new SecurityAppRegUserRolePath(this, null, Keys.FK4_APP_REG_ROLE_CLIENT_TYPE.getInverseKey());

        return _securityAppRegUserRole;
    }

    @Override
    public SecurityClientType as(String alias) {
        return new SecurityClientType(DSL.name(alias), this);
    }

    @Override
    public SecurityClientType as(Name alias) {
        return new SecurityClientType(alias, this);
    }

    @Override
    public SecurityClientType as(Table<?> alias) {
        return new SecurityClientType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientType rename(String name) {
        return new SecurityClientType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientType rename(Name name) {
        return new SecurityClientType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientType rename(Table<?> name) {
        return new SecurityClientType(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientType where(Condition condition) {
        return new SecurityClientType(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientType where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientType where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientType where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientType where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientType where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientType where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientType where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientType whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientType whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
