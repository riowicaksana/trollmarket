package com.trollmarket.service;

import com.trollmarket.dto.shipment.ShipmentGridDTO;
import com.trollmarket.dto.shipment.UpsertShipmentDTO;
import com.trollmarket.entity.Account;
import com.trollmarket.entity.Shipment;
import com.trollmarket.repository.AccountRepository;
import com.trollmarket.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService{

    private AccountRepository accountRepository;

    private ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(AccountRepository accountRepository, ShipmentRepository shipmentRepository) {
        this.accountRepository = accountRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public List<ShipmentGridDTO> findAll() {
        List<Shipment> shipmentList = shipmentRepository.findAll();

        List<ShipmentGridDTO> shipmentGridDTOList = new ArrayList<>();
        for(Shipment shipment:shipmentList){
            ShipmentGridDTO shipmentGridDTO = new ShipmentGridDTO(
                    shipment.getId(),
                    shipment.getName(),
                    shipment.getBalanceInRp(),
                    shipment.getPrice(),
                    shipment.getService(),
                    shipment.shipmentServiceConvert()
            );
            shipmentGridDTOList.add(shipmentGridDTO);
        }

        return shipmentGridDTOList;
    }

    @Override
    public void saveShipment(UpsertShipmentDTO upsertShipmentDTO,String name) {
    Account account = accountRepository.findById(name).get();

    Shipment shipment = new Shipment(
            upsertShipmentDTO.getId(),
            upsertShipmentDTO.getName(),
            upsertShipmentDTO.getPrice(),
            upsertShipmentDTO.getService(),
            account);

    shipmentRepository.save(shipment);

    }

    @Override
    public void deleteById(Long Id) {
        shipmentRepository.deleteById(Id);
    }

    @Override
    public void setServiceById(Long Id) {
        Shipment shipment = shipmentRepository.findById(Id).get();

        shipment.setService(Boolean.FALSE);

        shipmentRepository.save(shipment);

    }
}
