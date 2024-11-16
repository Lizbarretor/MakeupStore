package com.example.makeupstore.services;

import com.example.makeupstore.entities.ClientEntity;
import com.example.makeupstore.entities.SaleEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService {
    private final List<SaleEntity> sales;

    public SaleService() {
        sales = new ArrayList<>();
        sales.add(new SaleEntity(UUID.randomUUID(),1,"Efectivo", 15.00));
        sales.add(new SaleEntity(UUID.randomUUID(),2,"Tarjeta", 20.00));
        sales.add(new SaleEntity(UUID.randomUUID(),3,"Tranferencia", 12.00));

    }
    public List<SaleEntity> createSale(SaleEntity newSale) {
        newSale.setId(UUID.randomUUID());
        sales.add(newSale);
        return (List<SaleEntity>) newSale;
    }
    public List<SaleEntity> getAllSales() {
        return sales;
    }

    public Optional<SaleEntity> getSaleById(UUID id) {
        return sales.stream().filter(sale -> sale.getId().equals(id)).findFirst();
    }

    public Optional<SaleEntity> updateSale(UUID id, SaleEntity updatedSale) {
        Optional<SaleEntity> existingSale= getSaleById(id);

        if (existingSale.isPresent()) {
            SaleEntity sale = existingSale.get();
            sale.setIdClient(updatedSale.getIdClient());
            sale.setPayment(updatedSale.getPayment());
            sale.setTotal(updatedSale.getTotal());
            return Optional.of(sale);
        }
        return Optional.empty();
    }

    public Optional<SaleEntity> deleteSale(UUID id) {
        Optional<SaleEntity> saleToDelete = getSaleById(id);
        saleToDelete.ifPresent(sales::remove);
        return saleToDelete;
    }

}