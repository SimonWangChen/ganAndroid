package com.proclassmates.ganandroid.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SON".
*/
public class SonDao extends AbstractDao<Son, Long> {

    public static final String TABLENAME = "SON";

    /**
     * Properties of entity Son.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Name = new Property(0, String.class, "name", false, "NAME");
        public final static Property Age = new Property(1, Integer.class, "age", false, "AGE");
        public final static Property Id = new Property(2, Long.class, "id", true, "_id");
        public final static Property FatherId = new Property(3, Long.class, "fatherId", false, "FATHER_ID");
    }

    private DaoSession daoSession;


    public SonDao(DaoConfig config) {
        super(config);
    }
    
    public SonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SON\" (" + //
                "\"NAME\" TEXT," + // 0: name
                "\"AGE\" INTEGER," + // 1: age
                "\"_id\" INTEGER PRIMARY KEY ," + // 2: id
                "\"FATHER_ID\" INTEGER);"); // 3: fatherId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Son entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(2, age);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(3, id);
        }
 
        Long fatherId = entity.getFatherId();
        if (fatherId != null) {
            stmt.bindLong(4, fatherId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Son entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(2, age);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(3, id);
        }
 
        Long fatherId = entity.getFatherId();
        if (fatherId != null) {
            stmt.bindLong(4, fatherId);
        }
    }

    @Override
    protected final void attachEntity(Son entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2);
    }    

    @Override
    public Son readEntity(Cursor cursor, int offset) {
        Son entity = new Son( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // name
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // age
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // id
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // fatherId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Son entity, int offset) {
        entity.setName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setAge(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setFatherId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Son entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Son entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Son entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getFatherDao().getAllColumns());
            builder.append(" FROM SON T");
            builder.append(" LEFT JOIN FATHER T0 ON T.\"FATHER_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Son loadCurrentDeep(Cursor cursor, boolean lock) {
        Son entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Father father = loadCurrentOther(daoSession.getFatherDao(), cursor, offset);
        entity.setFather(father);

        return entity;    
    }

    public Son loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Son> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Son> list = new ArrayList<Son>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Son> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Son> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
