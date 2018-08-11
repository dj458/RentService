package com.uberrent.core.repository;

import org.junit.Test;
import com.uberrent.core.domain.Equipment;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EquipmentRepositoryTest extends RepositoryTest {
    @Test
    @Transactional
    public void findByIDTest(){
        Equipment e = new Equipment();
        e.setPriceValue("100");
        equipmentRepository.save(e);
        Optional<Equipment> testEquipment = equipmentRepository.findById(e.getId());
        assertNotNull(testEquipment);
        assertEquals(e.getId(),testEquipment.get().getId());
    }
}
