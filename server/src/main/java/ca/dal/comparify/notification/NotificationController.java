package ca.dal.comparify.notification;

import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.notification.model.NotificationChannelType;
import ca.dal.comparify.notification.model.NotificationReceiverModel;
import ca.dal.comparify.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * @author Harsh Shah
 */
@RestController()
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * @param receiverModel
     *
     * @author Harsh Shah
     * @return
     */
    @PostMapping("/receiver/register")
    public Map<String, Boolean> registerReceiver(@RequestBody NotificationReceiverModel receiverModel){
        return Collections.singletonMap(ApplicationConstant.STATUS,
            notificationService.registerReceiver(SecurityUtils.getPrincipal(SecurityContextHolder.getContext()), receiverModel));
    }

    /**
     *
     * @author Harsh Shah
     */
//    @PostMapping("/send")
//    public void registerReceiver(@RequestParam("type") String type){
//        notificationService.send(SecurityUtils.getPrincipal(SecurityContextHolder.getContext()),
//            NotificationChannelType.valueOf(type));
//    }
}
