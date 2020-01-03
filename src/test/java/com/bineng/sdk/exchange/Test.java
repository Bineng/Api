package com.bineng.sdk.exchange;

import com.bineng.sdk.client.PrivateApiClient;
import com.bineng.sdk.common.domain.ClientParameter;
import com.bineng.sdk.exchange.domain.OrdersParam;
import com.bineng.sdk.exchange.service.AccountService;
import com.bineng.sdk.exchange.service.OrderService;
import com.bineng.sdk.exchange.service.PublicService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class Test {
    public static void main(String args[]) throws Exception {
        ClientParameter parameter = ClientParameter.builder()
                .apiKey("c34bd651e0953b72df1c1593283f3fdc")
                .secretKey("b446a618875a0574dc348a25788ed768f168b423dafe56347125c574da4abede")
                .passphrase("123456")
                .baseUrl("http://bineng.bdw.top")
                .build();

        AccountService accountService = PrivateApiClient.builder()
                .configuration(parameter)
                .build().exchange().biz().account();

        OrderService orderService = PrivateApiClient.builder()
                .configuration(parameter)
                .build().exchange().biz().order();

        PublicService publicService = PrivateApiClient.builder()
                .configuration(parameter)
                .build().exchange().biz().pub();

        System.out.println(JSON.toJSONString(accountService.assetsList()));

        System.out.println(JSON.toJSONString(accountService.assets("BTC")));

        System.out.println(JSON.toJSONString(accountService.billList(1, 10, null, null, null, null, false)));

        System.out.println(JSON.toJSONString(orderService.postOrder("ETH_BTC", OrdersParam.builder()
                .side("buy")
                .price(new BigDecimal(1))
                .volume(new BigDecimal(1))
                .systemOrderType("limit")
                .source("web")
                .build())));

        System.out.println(JSON.toJSONString(publicService.getTime()));

        System.out.println(JSON.toJSONString(publicService.currencies()));

        System.out.println(JSON.toJSONString(publicService.candles("ETH_BTC", "15min",
                String.valueOf(DateUtils.addDays(new Date(), -1).getTime()), String.valueOf(new Date().getTime()))));

        System.out.println(JSON.toJSONString(publicService.ticker("ETH_BTC")));

        System.out.println(JSON.toJSONString(publicService.orderBook("ETH_BTC", 10)));

        System.out.println(JSON.toJSONString(publicService.fills("ETH_BTC", 10)));

    }
}
