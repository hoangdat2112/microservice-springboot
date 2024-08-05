package com.example.order_service.service.Impl;

import com.example.order_service.dto.OrderLineItemsDTO;
import com.example.order_service.dto.OrderRequest;
import com.example.order_service.dto.response.InventoryResponse;
import com.example.order_service.event.OrderPlacedEvent;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderLineItems;
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
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItems(orderLineItems);
        List<String> skuCodes=order.getOrderLineItems().stream()
                        .map(OrderLineItems::getSkuCode)
                        .toList();
        List<InventoryResponse> result= inventoryClient.isInStock(skuCodes);
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
            kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
        }else{
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }


    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItems;
    }
}
