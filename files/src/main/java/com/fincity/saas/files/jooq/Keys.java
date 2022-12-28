/*
 * This file is generated by jOOQ.
 */
package com.fincity.saas.files.jooq;


import com.fincity.saas.files.jooq.tables.FilesAccessPath;
import com.fincity.saas.files.jooq.tables.FilesSecuredAccessKey;
import com.fincity.saas.files.jooq.tables.records.FilesAccessPathRecord;
import com.fincity.saas.files.jooq.tables.records.FilesSecuredAccessKeyRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * files.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FilesAccessPathRecord> KEY_FILES_ACCESS_PATH_PRIMARY = Internal.createUniqueKey(FilesAccessPath.FILES_ACCESS_PATH, DSL.name("KEY_files_access_path_PRIMARY"), new TableField[] { FilesAccessPath.FILES_ACCESS_PATH.ID }, true);
    public static final UniqueKey<FilesSecuredAccessKeyRecord> KEY_FILES_SECURED_ACCESS_KEY_ACCESS_KEY_UNIQUE = Internal.createUniqueKey(FilesSecuredAccessKey.FILES_SECURED_ACCESS_KEY, DSL.name("KEY_files_secured_access_key_access_key_UNIQUE"), new TableField[] { FilesSecuredAccessKey.FILES_SECURED_ACCESS_KEY.ACCESS_KEY }, true);
    public static final UniqueKey<FilesSecuredAccessKeyRecord> KEY_FILES_SECURED_ACCESS_KEY_PRIMARY = Internal.createUniqueKey(FilesSecuredAccessKey.FILES_SECURED_ACCESS_KEY, DSL.name("KEY_files_secured_access_key_PRIMARY"), new TableField[] { FilesSecuredAccessKey.FILES_SECURED_ACCESS_KEY.ID }, true);
}
