package me.carda.awesome_notifications.notifications.models;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import me.carda.awesome_notifications.Definitions;
import me.carda.awesome_notifications.notifications.enumerators.ActionButtonType;
import me.carda.awesome_notifications.notifications.exceptions.AwesomeNotificationException;
import me.carda.awesome_notifications.utils.StringUtils;

public class NotificationButtonModel extends Model {

    public String key;
    public String icon;
    public String label;
    public Boolean enabled;
    public Boolean autoCancel;
    public Boolean showInCompactView;
    public ActionButtonType buttonType;

    public NotificationButtonModel(){}

    @Override
    public NotificationButtonModel fromMap(Map<String, Object> arguments) {

        key        = getValueOrDefault(arguments, Definitions.NOTIFICATION_BUTTON_KEY, String.class);
        icon       = getValueOrDefault(arguments, Definitions.NOTIFICATION_BUTTON_ICON, String.class);
        label      = getValueOrDefault(arguments, Definitions.NOTIFICATION_BUTTON_LABEL, String.class);

        buttonType = getEnumValueOrDefault(arguments, Definitions.NOTIFICATION_BUTTON_TYPE,
                ActionButtonType.class, ActionButtonType.values());

        enabled = getValueOrDefault(arguments, Definitions.NOTIFICATION_ENABLED, Boolean.class);
        autoCancel = getValueOrDefault(arguments, Definitions.NOTIFICATION_AUTO_CANCEL, Boolean.class);
        showInCompactView = getValueOrDefault(arguments, Definitions.NOTIFICATION_SHOW_IN_COMPACT_VIEW, Boolean.class);

        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> returnedObject = new HashMap<>();

        returnedObject.put(Definitions.NOTIFICATION_BUTTON_KEY, key);
        returnedObject.put(Definitions.NOTIFICATION_BUTTON_ICON, icon);
        returnedObject.put(Definitions.NOTIFICATION_BUTTON_LABEL, label);

        returnedObject.put(Definitions.NOTIFICATION_BUTTON_TYPE,
                this.buttonType != null ? this.buttonType.toString() : ActionButtonType.Default.toString());

        returnedObject.put(Definitions.NOTIFICATION_ENABLED, enabled);
        returnedObject.put(Definitions.NOTIFICATION_AUTO_CANCEL, autoCancel);
        returnedObject.put(Definitions.NOTIFICATION_SHOW_IN_COMPACT_VIEW, showInCompactView);

        return returnedObject;
    }

    @Override
    public String toJson() {
        return templateToJson();
    }

    @Override
    public NotificationButtonModel fromJson(String json){
        return (NotificationButtonModel) super.templateFromJson(json);
    }

    @Override
    public void validate(Context context) throws AwesomeNotificationException {
        if(StringUtils.isNullOrEmpty(key))
            throw new AwesomeNotificationException("Button action key cannot be null or empty");

        if(StringUtils.isNullOrEmpty(label))
            throw new AwesomeNotificationException("Button label cannot be null or empty");
    }
}
