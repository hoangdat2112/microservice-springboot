package com.example.order_service.service.Impl;

import com.example.event.OrderPlacedEvent;
import com.example.order_service.dto.OrderDTO;
import com.example.order_service.dto.response.InventoryResponse;

import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.repository.httpclient.InventoryClient;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate kafkaTemplate;
    private final OrderMapper orderMapper;
    @Override
    public void placeOrder(OrderDTO orderRequest) {
        Order order=orderMapper.entityFromRequest(orderRequest);
        List<InventoryResponse> result= inventoryClient.isInStock(orderRequest.getSkuCodes());
        boolean b=true;
        if(result.size()==0){
            b=false;
        }
        for(InventoryResponse x:result){
            if(!x.isInstock()){
                b=false;
                break;
            }
        }
        if(b){
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic",order.getEmail());
        }else{
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }


    }

}
