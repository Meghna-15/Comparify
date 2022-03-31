package ca.dal.comparify.notification;

import ca.dal.comparify.constant.NotificationConstant;
import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.notification.mail.MailService;
import ca.dal.comparify.framework.notification.push.WebPushNotificationService;
import ca.dal.comparify.framework.notification.websocket.WebSocketService;
import ca.dal.comparify.notification.model.*;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Harsh Shah
 */
@Service
public class NotificationService {

    @Autowired
    private ApplicationScope applicationScope;

    @Autowired
    private WebPushNotificationService webPushNotificationService;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private MailService mailService;

    /**
     *
     * @param userId
     * @param receiverModel
     *
     * @author Harsh Shah
     * @return
     */
    public boolean registerReceiver(String userId, NotificationReceiverModel receiverModel) {
        applicationScope.setUserToReceiverToken(userId, receiverModel.getIdentifier());

//        try {
//            TopicManagementResponse response = FirebaseMessaging.getInstance()
//                .subscribeToTopic(Collections.singletonList(receiverModel.getIdentifier()),
//                    NotificationConstant.TOPIC);
//
//            return response.getSuccessCount() == 1;
//
//        } catch (FirebaseMessagingException e) {
//            return false;
//        }

        return true;
    }


    public boolean send(String userId, NotificationChannelType type, AbstractChannelNotificationModel model){

        if(NotificationChannelType.PUSH.equals(type)){
            return webPushNotificationService.send(userId, (WebPushNotificationModel) model);
        } else if(NotificationChannelType.SOCKET.equals(type)){
            return webSocketService.send(userId, (WebSocketNotificationModel) model);
        } else if (NotificationChannelType.MAIL.equals(type)){
            return mailService.send(null, null, null, null);
        }

        return true;

    }
}
