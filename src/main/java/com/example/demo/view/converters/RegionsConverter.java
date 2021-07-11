package com.example.demo.view.converters;

import com.example.demo.entities.Regions;
import com.example.demo.view.RegionsDTO;
import org.springframework.stereotype.Service;

@Service
public class RegionsConverter {
    public Regions toRegions(RegionsDTO regionsDTO) {
        Regions regions = new Regions();

        regions.setRegionId(regionsDTO.getRegionId());
        regions.setRegionEndAddress(regionsDTO.getRegionEndAddress());
        regions.setRegionBeginAddress(regionsDTO.getRegionBeginAddress());

        return regions;
    }

    public RegionsDTO toRegionsDTO(Regions regions) {
        RegionsDTO regionsDTO = new RegionsDTO();

        regionsDTO.setRegionId(regions.getRegionId());
        regionsDTO.setRegionEndAddress(regions.getRegionEndAddress());
        regionsDTO.setRegionBeginAddress(regions.getRegionBeginAddress());

        return regionsDTO;
    }
}
