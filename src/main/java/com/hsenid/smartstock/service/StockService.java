package com.hsenid.smartstock.service;

import com.hsenid.smartstock.dto.mapper.StockMapper;
import com.hsenid.smartstock.dto.request.StockRequest;
import com.hsenid.smartstock.dto.response.StockResponse;
import com.hsenid.smartstock.entity.Stock;
import com.hsenid.smartstock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    public Optional<StockResponse> createStock(StockRequest stockRequest) {
        Stock stock = stockMapper.toStock(stockRequest);
        Stock savedStock = stockRepository.save(stock);
        return Optional.of(stockMapper.toStockResponse(savedStock));
    }

    public Optional<StockResponse> updateStock(String id, StockRequest stockRequest) {
        Optional<Stock> stockOptional = stockRepository.findById(id);
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            stock.setProductId(stockRequest.productId());
            stock.setQuantity(stockRequest.quantity());
            stock.setName(stockRequest.name());
            stock.set_Order(stockRequest.isOrder());
            Stock updatedStock = stockRepository.save(stock);
            return Optional.of(stockMapper.toStockResponse(updatedStock));
        }
        return Optional.empty();
    }

    public void deleteStock(String id) {
        stockRepository.deleteById(id);
    }

    public List<StockResponse> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(stockMapper::toStockResponse)
                .collect(Collectors.toList());
    }

    public Optional<StockResponse> getStockById(String id) {
        return stockRepository.findById(id)
                .map(stockMapper::toStockResponse);
    }
}
