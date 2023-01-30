package com.trollmarket.controller;

import com.trollmarket.dto.shipment.ShipmentGridDTO;
import com.trollmarket.dto.shipment.UpsertShipmentDTO;
import com.trollmarket.service.ProductService;
import com.trollmarket.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("shipment")
public class ShipmentController {

    private ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/shipment-table")
    public String getAllShipment(Model model, Principal principal){
        List<ShipmentGridDTO> shipmentGridDTO = shipmentService.findAll();

        UpsertShipmentDTO upsertShipmentDTO = new UpsertShipmentDTO();


        model.addAttribute("shipment",upsertShipmentDTO);
        model.addAttribute("shipmentList",shipmentGridDTO);


        return "shipment/shipment-table";
    }

    @PostMapping("/save-shipment")
    public String saveShipment(@ModelAttribute("shipment") UpsertShipmentDTO upsertShipmentDTO,Principal principal){
        String name = principal.getName();
        shipmentService.saveShipment(upsertShipmentDTO,name);

        return "redirect:/shipment/shipment-table";
    }

    @GetMapping("/delete/{Id}")
    public String deletShipment(@PathVariable Long Id){
        shipmentService.deleteById(Id);
        return "redirect:/shipment/shipment-table";
    }

    @GetMapping("/stop-service/{Id}")
    public String stopServiceShipment(@PathVariable Long Id){
        shipmentService.setServiceById(Id);

        return "redirect:/shipment/shipment-table";
    }

}
