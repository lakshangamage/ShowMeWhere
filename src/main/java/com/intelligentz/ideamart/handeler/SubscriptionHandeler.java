package com.intelligentz.ideamart.handeler;

import com.intelligentz.ideamart.contoller.SubscriptionController;
import com.intelligentz.ideamart.contoller.UsernameContoller;
import com.intelligentz.ideamart.db_model.SubscriptionUsername;
import com.intelligentz.ideamart.ideamart.model.subscription.SubscriptionNotificationInRequest;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by lakshan on 12/8/16.
 */
public class SubscriptionHandeler extends AbstractDatabaseHandeler {


    public int addSubscription (SubscriptionNotificationInRequest subscription)
            throws IOException, PropertyVetoException {
        boolean isSuccess = false;
        int pin = 0;
        try {
            isSuccess = SubscriptionController.addSubscription (getConnection(), subscription);
            if (isSuccess) {
                isSuccess = UsernameContoller.addNewSubscriberPin(getConnection(), subscription.getSubscriberId());
                if (isSuccess) {
                    pin = UsernameContoller.getSubscriptionUsername(
                            getConnection(), subscription.getSubscriberId()).getPin();
                }
            }
        } catch (SQLException ex){
            rollback(ex);
        } finally {
            closeConnection();
        }
        return pin;
    }

    public SubscriptionNotificationInRequest getSubscription(SubscriptionNotificationInRequest subscription)
            throws PropertyVetoException, IOException {
        SubscriptionNotificationInRequest currentSubscription = null;
        try {
            currentSubscription =
                    SubscriptionController.getSubscriber(getConnection(), subscription);
        } catch (SQLException ex) {
            rollback(ex);
        } finally {
            closeConnection();
        }
        return currentSubscription;
    }

    public SubscriptionUsername registerExistingSubscriber(SubscriptionNotificationInRequest subscription)
            throws PropertyVetoException, IOException {
        SubscriptionUsername subscriptionUsername = new SubscriptionUsername();
        try {
            if (SubscriptionController.updateExistingSubscription(getConnection(), subscription)) {
                subscriptionUsername = UsernameContoller.getSubscriptionUsername(getConnection(), subscription.getSubscriberId());
            }
        } catch (SQLException ex){
            rollback(ex);
        } finally {
            closeConnection();
        }
        return subscriptionUsername;
    }

}
