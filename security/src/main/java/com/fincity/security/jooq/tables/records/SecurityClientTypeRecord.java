/*
 * This file is generated by jOOQ.
 */
package com.fincity.security.jooq.tables.records;


import com.fincity.security.jooq.tables.SecurityClientType;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SecurityClientTypeRecord extends UpdatableRecordImpl<SecurityClientTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>security.security_client_type.ID</code>. Primary key
     */
    public SecurityClientTypeRecord setId(ULong value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.ID</code>. Primary key
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>security.security_client_type.CODE</code>. Code
     */
    public SecurityClientTypeRecord setCode(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.CODE</code>. Code
     */
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>security.security_client_type.TYPE</code>. Type
     */
    public SecurityClientTypeRecord setType(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.TYPE</code>. Type
     */
    public String getType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>security.security_client_type.DESCRIPTION</code>.
     * Description of the client type
     */
    public SecurityClientTypeRecord setDescription(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.DESCRIPTION</code>.
     * Description of the client type
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>security.security_client_type.CREATED_BY</code>. ID of
     * the user who created this row
     */
    public SecurityClientTypeRecord setCreatedBy(ULong value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.CREATED_BY</code>. ID of
     * the user who created this row
     */
    public ULong getCreatedBy() {
        return (ULong) get(4);
    }

    /**
     * Setter for <code>security.security_client_type.CREATED_AT</code>. Time
     * when this row is created
     */
    public SecurityClientTypeRecord setCreatedAt(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.CREATED_AT</code>. Time
     * when this row is created
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>security.security_client_type.UPDATED_BY</code>. ID of
     * the user who updated this row
     */
    public SecurityClientTypeRecord setUpdatedBy(ULong value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.UPDATED_BY</code>. ID of
     * the user who updated this row
     */
    public ULong getUpdatedBy() {
        return (ULong) get(6);
    }

    /**
     * Setter for <code>security.security_client_type.UPDATED_AT</code>. Time
     * when this row is updated
     */
    public SecurityClientTypeRecord setUpdatedAt(LocalDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>security.security_client_type.UPDATED_AT</code>. Time
     * when this row is updated
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SecurityClientTypeRecord
     */
    public SecurityClientTypeRecord() {
        super(SecurityClientType.SECURITY_CLIENT_TYPE);
    }

    /**
     * Create a detached, initialised SecurityClientTypeRecord
     */
    public SecurityClientTypeRecord(ULong id, String code, String type, String description, ULong createdBy, LocalDateTime createdAt, ULong updatedBy, LocalDateTime updatedAt) {
        super(SecurityClientType.SECURITY_CLIENT_TYPE);

        setId(id);
        setCode(code);
        setType(type);
        setDescription(description);
        setCreatedBy(createdBy);
        setCreatedAt(createdAt);
        setUpdatedBy(updatedBy);
        setUpdatedAt(updatedAt);
        resetChangedOnNotNull();
    }
}
