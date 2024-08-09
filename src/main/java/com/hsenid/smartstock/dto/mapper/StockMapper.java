package com.hsenid.smartstock.dto.mapper;

import com.hsenid.smartstock.dto.request.StockRequest;
import com.hsenid.smartstock.dto.response.StockResponse;
import com.hsenid.smartstock.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockResponse toStockResponse(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getProductId(),
                stock.getQuantity(),
                stock.getName(),
                stock.is_Order()
        );
    }

    public Stock toStock(StockRequest stockRequest) {
        Stock stock = new Stock();
        stock.setProductId(stockRequest.productId());
        stock.setQuantity(stockRequest.quantity());
        stock.setName(stockRequest.name());
        stock.set_Order(stockRequest.isOrder());
        return stock;
    }
}
