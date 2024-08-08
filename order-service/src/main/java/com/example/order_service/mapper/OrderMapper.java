package com.example.order_service.mapper;

import com.example.order_service.dto.OrderDTO;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.model.Order;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true), imports = {String.class})
public abstract class OrderMapper {
    public abstract Order entityFromRequest(OrderDTO request);
    public abstract OrderResponse orderResponse(Order request);

}
