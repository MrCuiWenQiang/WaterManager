package io.renren.modules.sys.entity.request;

import lombok.Data;

@Data
public class SubscriptionServiceEntity {
    private String serviceId;
    private String serviceType;
    private SubscriptionDataEntity data;
    private String eventTime;

}
