package com.example.demo.view.converters;

import com.example.demo.entities.ContentOfKit;
import com.example.demo.entities.ContentVendors;
import com.example.demo.repo.ContentVendorsRepository;
import com.example.demo.view.ContentOfKitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentOfKitConverter {
    public final ContentVendorsRepository contentVendorsRepository;

    @Autowired
    public ContentOfKitConverter(ContentVendorsRepository contentVendorsRepository) {
        this.contentVendorsRepository = contentVendorsRepository;
    }

    public ContentOfKit toContentOfKit(ContentOfKitDTO contentOfKitDTO) {
        ContentOfKit contentOfKit = new ContentOfKit();

        contentOfKit.setContentId(contentOfKitDTO.getContentId());
        contentOfKit.setContentTitle(contentOfKitDTO.getContentTitle());
        contentOfKit.setPricePerContentUnit(contentOfKitDTO.getPricePerContentUnit());
        contentOfKit.setDrug(contentOfKitDTO.isDrug());
        contentOfKit.setContentVendors(contentVendorsRepository.findById(contentOfKitDTO.getContentVendorsDtoID()).orElseThrow());

        return contentOfKit;
    }

    public ContentOfKitDTO toContentOfKitDTO(ContentOfKit contentOfKit) {
        ContentOfKitDTO contentOfKitDTO = new ContentOfKitDTO();

        contentOfKitDTO.setContentId(contentOfKit.getContentId());
        contentOfKitDTO.setContentTitle(contentOfKit.getContentTitle());
        contentOfKitDTO.setPricePerContentUnit(contentOfKit.getPricePerContentUnit());
        contentOfKitDTO.setDrug(contentOfKit.isDrug());
        contentOfKitDTO.setContentVendorsDtoID(contentOfKit.getContentVendors().getContentVendorId());

        return contentOfKitDTO;
    }
}
