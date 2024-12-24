/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables;


import com.fincity.security.jooq.Keys;
import com.fincity.security.jooq.Security;
import com.fincity.security.jooq.tables.SecurityClient.SecurityClientPath;
import com.fincity.security.jooq.tables.records.SecurityClientManageRecord;

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
public class SecurityClientManage extends TableImpl<SecurityClientManageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>security.security_client_manage</code>
     */
    public static final SecurityClientManage SECURITY_CLIENT_MANAGE = new SecurityClientManage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SecurityClientManageRecord> getRecordType() {
        return SecurityClientManageRecord.class;
    }

    /**
     * The column <code>security.security_client_manage.ID</code>. Primary key
     */
    public final TableField<SecurityClientManageRecord, ULong> ID = createField(DSL.name("ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key");

    /**
     * The column <code>security.security_client_manage.CLIENT_ID</code>. Client
     * ID
     */
    public final TableField<SecurityClientManageRecord, ULong> CLIENT_ID = createField(DSL.name("CLIENT_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Client ID");

    /**
     * The column <code>security.security_client_manage.MANAGE_CLIENT_ID</code>.
     * Client ID that manages this client
     */
    public final TableField<SecurityClientManageRecord, ULong> MANAGE_CLIENT_ID = createField(DSL.name("MANAGE_CLIENT_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Client ID that manages this client");

    private SecurityClientManage(Name alias, Table<SecurityClientManageRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SecurityClientManage(Name alias, Table<SecurityClientManageRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>security.security_client_manage</code> table
     * reference
     */
    public SecurityClientManage(String alias) {
        this(DSL.name(alias), SECURITY_CLIENT_MANAGE);
    }

    /**
     * Create an aliased <code>security.security_client_manage</code> table
     * reference
     */
    public SecurityClientManage(Name alias) {
        this(alias, SECURITY_CLIENT_MANAGE);
    }

    /**
     * Create a <code>security.security_client_manage</code> table reference
     */
    public SecurityClientManage() {
        this(DSL.name("security_client_manage"), null);
    }

    public <O extends Record> SecurityClientManage(Table<O> path, ForeignKey<O, SecurityClientManageRecord> childPath, InverseForeignKey<O, SecurityClientManageRecord> parentPath) {
        super(path, childPath, parentPath, SECURITY_CLIENT_MANAGE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SecurityClientManagePath extends SecurityClientManage implements Path<SecurityClientManageRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SecurityClientManagePath(Table<O> path, ForeignKey<O, SecurityClientManageRecord> childPath, InverseForeignKey<O, SecurityClientManageRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SecurityClientManagePath(Name alias, Table<SecurityClientManageRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SecurityClientManagePath as(String alias) {
            return new SecurityClientManagePath(DSL.name(alias), this);
        }

        @Override
        public SecurityClientManagePath as(Name alias) {
            return new SecurityClientManagePath(alias, this);
        }

        @Override
        public SecurityClientManagePath as(Table<?> alias) {
            return new SecurityClientManagePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Security.SECURITY;
    }

    @Override
    public Identity<SecurityClientManageRecord, ULong> getIdentity() {
        return (Identity<SecurityClientManageRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<SecurityClientManageRecord> getPrimaryKey() {
        return Keys.KEY_SECURITY_CLIENT_MANAGE_PRIMARY;
    }

    @Override
    public List<ForeignKey<SecurityClientManageRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK1_CLIENT_MANAGE_CLIENT_ID, Keys.FK1_CLIENT_MANAGE_MNG_CLIENT_ID);
    }

    private transient SecurityClientPath _fk1ClientManageClientId;

    /**
     * Get the implicit join path to the <code>security.security_client</code>
     * table, via the <code>FK1_CLIENT_MANAGE_CLIENT_ID</code> key.
     */
    public SecurityClientPath fk1ClientManageClientId() {
        if (_fk1ClientManageClientId == null)
            _fk1ClientManageClientId = new SecurityClientPath(this, Keys.FK1_CLIENT_MANAGE_CLIENT_ID, null);

        return _fk1ClientManageClientId;
    }

    private transient SecurityClientPath _fk1ClientManageMngClientId;

    /**
     * Get the implicit join path to the <code>security.security_client</code>
     * table, via the <code>FK1_CLIENT_MANAGE_MNG_CLIENT_ID</code> key.
     */
    public SecurityClientPath fk1ClientManageMngClientId() {
        if (_fk1ClientManageMngClientId == null)
            _fk1ClientManageMngClientId = new SecurityClientPath(this, Keys.FK1_CLIENT_MANAGE_MNG_CLIENT_ID, null);

        return _fk1ClientManageMngClientId;
    }

    @Override
    public SecurityClientManage as(String alias) {
        return new SecurityClientManage(DSL.name(alias), this);
    }

    @Override
    public SecurityClientManage as(Name alias) {
        return new SecurityClientManage(alias, this);
    }

    @Override
    public SecurityClientManage as(Table<?> alias) {
        return new SecurityClientManage(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientManage rename(String name) {
        return new SecurityClientManage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientManage rename(Name name) {
        return new SecurityClientManage(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SecurityClientManage rename(Table<?> name) {
        return new SecurityClientManage(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientManage where(Condition condition) {
        return new SecurityClientManage(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientManage where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientManage where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientManage where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientManage where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientManage where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientManage where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SecurityClientManage where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientManage whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SecurityClientManage whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
