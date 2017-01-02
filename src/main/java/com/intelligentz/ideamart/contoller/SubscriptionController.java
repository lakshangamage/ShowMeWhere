package com.intelligentz.ideamart.contoller;

import com.intelligentz.ideamart.ideamart.model.subscription.SubscriptionNotificationInRequest;
import org.apache.commons.dbutils.DbUtils;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lakshan on 12/6/16.
 */
public class SubscriptionController {

    private static final String COLUMN_APPLICATION_ID = "applicationId";
    private static final String COLUMN_FREQUENCY = "frequency";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_SUBSCRIBER_ID = "subscriberId";
    private static final String COLUMN_VERSION= "version";

    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static boolean addSubscription(Connection connection,
                                          SubscriptionNotificationInRequest subscription)
                                        throws SQLException {
        boolean status;
        try {
            String SQL1 = "INSERT INTO showmewhere_subscription " +
                    "(applicationId, frequency, status, subscriberId, version, timeStamp) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, subscription.getApplicationId());
            preparedStatement.setString(2, subscription.getFrequency());
            preparedStatement.setString(3, subscription.getStatus());
            preparedStatement.setString(4, subscription.getSubscriberId());
            preparedStatement.setString(5, subscription.getVersion());
            preparedStatement.setString(6, subscription.getTimeStamp());

            status = 0 < preparedStatement.executeUpdate() ? true : false;
        }finally {
            DbUtils.closeQuietly(preparedStatement);
        }

        return status;
    }

    public static SubscriptionNotificationInRequest getSubscriber(Connection connection,
                                                                  SubscriptionNotificationInRequest subscription)
                                                                throws SQLException {

        SubscriptionNotificationInRequest currentSubscription = null;
        try{
            String SQL1 = "SELECT applicationId, frequency, status, subscriberId, version, \"timeStamp\" " +
                    "FROM showmewhere_subscription WHERE subscriberId = ?";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, subscription.getSubscriberId());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                currentSubscription = new SubscriptionNotificationInRequest();
                currentSubscription.setApplicationId(resultSet.getString(COLUMN_APPLICATION_ID));
                currentSubscription.setFrequency(resultSet.getString(COLUMN_FREQUENCY));
                currentSubscription.setStatus(resultSet.getString(COLUMN_STATUS));
                currentSubscription.setSubscriberId(resultSet.getString(COLUMN_SUBSCRIBER_ID));
                currentSubscription.setVersion(resultSet.getString(COLUMN_VERSION));
            }

        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
        }
        return currentSubscription;
    }

    public static boolean updateExistingSubscription(Connection connection,
                                                     SubscriptionNotificationInRequest subscription)
                                                    throws SQLException {

        boolean status;
        try {
            String SQL1 = "UPDATE showmewhere_subscription SET status = ? " +
                    "WHERE subscriberId = ?";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, subscription.getStatus());
            preparedStatement.setString(2, subscription.getSubscriberId());

            status = 0 < preparedStatement.executeUpdate() ? true : false;
        }finally {
            DbUtils.closeQuietly(preparedStatement);
        }

        return status;
    }

}
