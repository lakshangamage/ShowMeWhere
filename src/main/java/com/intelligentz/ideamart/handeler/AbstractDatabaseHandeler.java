package com.intelligentz.ideamart.handeler;

import com.intelligentz.ideamart.database.DBConnection;
import org.apache.commons.dbutils.DbUtils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lakshan on 12/9/16.
 */
public abstract class AbstractDatabaseHandeler {
    protected Connection connection;

    protected Connection getConnection() throws PropertyVetoException, SQLException, IOException {
        if (connection == null) {
            connection = DBConnection.getDBConnection().getConnection();
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(Boolean.FALSE);
            }
        }
        return connection;
    }

    protected void rollback (Exception ex) {
        try {
            DbUtils.rollback(connection);
            connection.setAutoCommit(Boolean.TRUE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
    }

    protected void closeConnection(){
        DbUtils.closeQuietly(connection);
    }

}
