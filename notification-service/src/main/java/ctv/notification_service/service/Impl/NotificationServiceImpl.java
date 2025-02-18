package ctv.notification_service.service.Impl;


import ctv.common_api.service.NotificationService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class NotificationServiceImpl implements NotificationService {
    @Override
    public String sendNotification(String message) {
        return "Message received: " + message;
    }
}