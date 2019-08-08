/**
 * Copyright 2019 bejson.com
 */
package io.renren.modules.sys.entity.request;

import lombok.Data;

/**
 * Auto-generated: 2019-08-08 16:29:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class SubscriptionEntity {

    private String notifyType;
    private String deviceId;
    private String gatewayId;
    private String requestId;
    private SubscriptionServiceEntity service;

}