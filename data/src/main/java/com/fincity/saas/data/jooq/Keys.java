/*
 * This file is generated by jOOQ.
 */
package com.fincity.saas.data.jooq;


import com.fincity.saas.data.jooq.tables.DataClientActivity;
import com.fincity.saas.data.jooq.tables.DataConnection;
import com.fincity.saas.data.jooq.tables.DataStorage;
import com.fincity.saas.data.jooq.tables.DataStorageActivity;
import com.fincity.saas.data.jooq.tables.DataStorageField;
import com.fincity.saas.data.jooq.tables.records.DataClientActivityRecord;
import com.fincity.saas.data.jooq.tables.records.DataConnectionRecord;
import com.fincity.saas.data.jooq.tables.records.DataStorageActivityRecord;
import com.fincity.saas.data.jooq.tables.records.DataStorageFieldRecord;
import com.fincity.saas.data.jooq.tables.records.DataStorageRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * data.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<DataClientActivityRecord> KEY_DATA_CLIENT_ACTIVITY_PRIMARY = Internal.createUniqueKey(DataClientActivity.DATA_CLIENT_ACTIVITY, DSL.name("KEY_data_client_activity_PRIMARY"), new TableField[] { DataClientActivity.DATA_CLIENT_ACTIVITY.ID }, true);
    public static final UniqueKey<DataClientActivityRecord> KEY_DATA_CLIENT_ACTIVITY_UK1_DATA_CLNT_ACT = Internal.createUniqueKey(DataClientActivity.DATA_CLIENT_ACTIVITY, DSL.name("KEY_data_client_activity_UK1_DATA_CLNT_ACT"), new TableField[] { DataClientActivity.DATA_CLIENT_ACTIVITY.CLIENT_CODE, DataClientActivity.DATA_CLIENT_ACTIVITY.ACTIVITY_ID }, true);
    public static final UniqueKey<DataConnectionRecord> KEY_DATA_CONNECTION_PRIMARY = Internal.createUniqueKey(DataConnection.DATA_CONNECTION, DSL.name("KEY_data_connection_PRIMARY"), new TableField[] { DataConnection.DATA_CONNECTION.ID }, true);
    public static final UniqueKey<DataConnectionRecord> KEY_DATA_CONNECTION_UK1_DATA_CONN = Internal.createUniqueKey(DataConnection.DATA_CONNECTION, DSL.name("KEY_data_connection_UK1_DATA_CONN"), new TableField[] { DataConnection.DATA_CONNECTION.CLIENT_CODE, DataConnection.DATA_CONNECTION.DB_NAME }, true);
    public static final UniqueKey<DataStorageRecord> KEY_DATA_STORAGE_PRIMARY = Internal.createUniqueKey(DataStorage.DATA_STORAGE, DSL.name("KEY_data_storage_PRIMARY"), new TableField[] { DataStorage.DATA_STORAGE.ID }, true);
    public static final UniqueKey<DataStorageActivityRecord> KEY_DATA_STORAGE_ACTIVITY_PRIMARY = Internal.createUniqueKey(DataStorageActivity.DATA_STORAGE_ACTIVITY, DSL.name("KEY_data_storage_activity_PRIMARY"), new TableField[] { DataStorageActivity.DATA_STORAGE_ACTIVITY.ID }, true);
    public static final UniqueKey<DataStorageFieldRecord> KEY_DATA_STORAGE_FIELD_PRIMARY = Internal.createUniqueKey(DataStorageField.DATA_STORAGE_FIELD, DSL.name("KEY_data_storage_field_PRIMARY"), new TableField[] { DataStorageField.DATA_STORAGE_FIELD.ID }, true);
    public static final UniqueKey<DataStorageFieldRecord> KEY_DATA_STORAGE_FIELD_UK1_DATA_STRG_FLD = Internal.createUniqueKey(DataStorageField.DATA_STORAGE_FIELD, DSL.name("KEY_data_storage_field_UK1_DATA_STRG_FLD"), new TableField[] { DataStorageField.DATA_STORAGE_FIELD.STORAGE_ID, DataStorageField.DATA_STORAGE_FIELD.NAME }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<DataClientActivityRecord, DataStorageActivityRecord> FK1_CLNT_ACT_STRG_ACT = Internal.createForeignKey(DataClientActivity.DATA_CLIENT_ACTIVITY, DSL.name("FK1_CLNT_ACT_STRG_ACT"), new TableField[] { DataClientActivity.DATA_CLIENT_ACTIVITY.ACTIVITY_ID }, Keys.KEY_DATA_STORAGE_ACTIVITY_PRIMARY, new TableField[] { DataStorageActivity.DATA_STORAGE_ACTIVITY.ID }, true);
    public static final ForeignKey<DataStorageActivityRecord, DataStorageRecord> FK1_STRG_ACT_STRG_ID = Internal.createForeignKey(DataStorageActivity.DATA_STORAGE_ACTIVITY, DSL.name("FK1_STRG_ACT_STRG_ID"), new TableField[] { DataStorageActivity.DATA_STORAGE_ACTIVITY.STORAGE_ID }, Keys.KEY_DATA_STORAGE_PRIMARY, new TableField[] { DataStorage.DATA_STORAGE.ID }, true);
    public static final ForeignKey<DataStorageFieldRecord, DataStorageRecord> FK1_STRG_FLD_STRG_ID = Internal.createForeignKey(DataStorageField.DATA_STORAGE_FIELD, DSL.name("FK1_STRG_FLD_STRG_ID"), new TableField[] { DataStorageField.DATA_STORAGE_FIELD.STORAGE_ID }, Keys.KEY_DATA_STORAGE_PRIMARY, new TableField[] { DataStorage.DATA_STORAGE.ID }, true);
    public static final ForeignKey<DataStorageFieldRecord, DataStorageFieldRecord> FK2_STRG_FLD_STRG_FILD = Internal.createForeignKey(DataStorageField.DATA_STORAGE_FIELD, DSL.name("FK2_STRG_FLD_STRG_FILD"), new TableField[] { DataStorageField.DATA_STORAGE_FIELD.REF_STORAGE_FIELD_ID }, Keys.KEY_DATA_STORAGE_FIELD_PRIMARY, new TableField[] { DataStorageField.DATA_STORAGE_FIELD.ID }, true);
}