package com.uberrent.core.service;

import com.uberrent.core.domain.Equipment;
import com.uberrent.core.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipmentService  {
    @Autowired
    private EquipmentRepository equipmentRepository;

    private Equipment findBy(Equipment equipment){
         Optional<Equipment> optional = equipmentRepository.findById(equipment.getId());
         Equipment result = optional.get();
         return result;
    }
}