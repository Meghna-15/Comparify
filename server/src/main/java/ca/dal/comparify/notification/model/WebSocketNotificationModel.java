package ca.dal.comparify.notification.model;

import java.util.List;

/**
 * @author Harsh Shah
 */
public class WebSocketNotificationModel extends AbstractChannelNotificationModel{

    private List<String> receiverIds;

    public WebSocketNotificationModel(List<String> receiverIds, String title,
                                      String message, IconType iconType,
                                      NotificationTypeEnum type) {
        super(title, message, iconType, type);
        this.receiverIds = receiverIds;
    }

    public List<String> getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(List<String> receiverIds) {
        this.receiverIds = receiverIds;
    }
}
