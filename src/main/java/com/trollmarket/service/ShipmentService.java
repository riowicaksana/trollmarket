package com.trollmarket.service;

import com.trollmarket.dto.shipment.ShipmentGridDTO;
import com.trollmarket.dto.shipment.UpsertShipmentDTO;

import java.util.List;

public interface ShipmentService {
    List<ShipmentGridDTO> findAll();

    void saveShipment(UpsertShipmentDTO upsertShipmentDTO,String name);

    void deleteById(Long Id);

    void setServiceById(Long Id);
}
