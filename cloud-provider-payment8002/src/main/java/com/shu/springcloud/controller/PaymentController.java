package com.shu.springcloud.controller;

import com.shu.springcloud.entities.CommonResult;
import com.shu.springcloud.entities.Payment;
import com.shu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult<>(200, "插入成功, port=" + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入失败", result);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功, port=" + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有记录", null);
        }
    }
}
