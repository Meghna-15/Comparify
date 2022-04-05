package ca.dal.comparify.framework.notification.push;

import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.properties.PushNotificationConfigurationProperties;
import ca.dal.comparify.framework.notification.model.WebPushNotificationModel;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import static ca.dal.comparify.constant.NotificationConstant.TTL;

/**
 * @author Harsh Shah
 */
@Service
public class WebPushNotificationService {

    @Autowired
    private PushNotificationConfigurationProperties pushNotificationConfigurationProperties;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationScope applicationScope;


    public WebPushNotificationService(PushNotificationConfigurationProperties pushNotificationConfigurationProperties,
                                      ResourceLoader resourceLoader) {

        Resource resource = resourceLoader.getResource(pushNotificationConfigurationProperties.getServiceAccountFile());

        try (InputStream serviceAccount = resource.getInputStream()) {

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {

        } catch (Exception e){

        }
    }

    /**
     * @param userId
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public boolean send(String userId, WebPushNotificationModel model) {

        String token = applicationScope.getUserToReceiverToken(userId);

        if(token == null){
            return false;
        }

        Message message = Message.builder()
            .setToken(token)
            .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", TTL)
                .setNotification(new WebpushNotification(model.getTitle(),
                    model.getMessage(),
                    model.getIconType().getValue()))
                .build())
            .build();

        try {
            FirebaseMessaging.getInstance().sendAsync(message).get();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        } catch (Exception e){
            return false;
        }

        return true;
    }
}
