package com.intelligentz.ideamart.contoller;

import com.intelligentz.ideamart.db_model.SubscriptionUsername;
import com.intelligentz.ideamart.ideamart.model.subscription.SubscriptionNotificationInRequest;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lakshan on 12/9/16.
 */
public class UsernameContoller {
    private static final String COLUMN_SUBSCRIBER_ID = "subscriberId";
    private static final String COLUMN_PIN = "pin";
    private static final String COLUMN_USERNAME = "username";

    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static boolean addNewSubscriberPin(Connection connection, String subscriberId)
            throws SQLException {
        boolean status = false;
        try {
            String SQL1 = "INSERT INTO showmewhere_username " +
                    "(subscriberId) VALUES (?)";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, subscriberId);

            status = 0 < preparedStatement.executeUpdate() ? true : false;
        }finally {
            DbUtils.closeQuietly(preparedStatement);
        }

        return status;
    }

    public static SubscriptionUsername getSubscriptionUsername(Connection connection, String subscriberId)
            throws SQLException {
        SubscriptionUsername currentUsername = null;
        try{
            String SQL1 = "SELECT subscriberId, pin, username " +
                    "FROM showmewhere_username WHERE subscriberId = ?";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, subscriberId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                currentUsername = new SubscriptionUsername();
                currentUsername.setSubscriberId(resultSet.getString(COLUMN_SUBSCRIBER_ID));
                currentUsername.setPin(resultSet.getInt(COLUMN_PIN));
                currentUsername.setUsername(resultSet.getString(COLUMN_USERNAME));
            }

        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
        }
        return currentUsername;
    }
}


