package io.renren.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @version V1.0
 * @Description: ${todo}
 * @Date 2019/7/20 0020
 **/
@Controller
public class DeviceHtmlController {
    @RequestMapping("/device/list")
    public String deviceList(ModelMap map){
        return "modules/sys/deviceList";
    }

    @RequestMapping("/device/query/{userId}")
    public String deviceQuery(@PathVariable String userId, ModelMap modelMap){
        modelMap.put("userId",userId);
        return "modules/sys/queryDevice";
    }
}
