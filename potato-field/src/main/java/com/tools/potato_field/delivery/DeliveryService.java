package com.tools.potato_field.service;

import com.tools.potato_field.entity.Delivery;
import com.tools.potato_field.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery findDelivery(Long id) {
        return deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    public List<Delivery> findAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}