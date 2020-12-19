package com.wannistudio.managementapi.service;

import com.wannistudio.managementapi.domain.Shipper;
import com.wannistudio.managementapi.repository.ShipperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShipperService {
    private final ShipperRepository shipperRepository;

    @Transactional
    public Long saveShipper(Shipper shipper) {
        shipperRepository.save(shipper);
        return shipper.getId();
    }

    public Shipper findOne(Long id) {
        return shipperRepository.findOne(id);
    }

    public List<Shipper> findAll() {
        return shipperRepository.findAll();
    }
}
