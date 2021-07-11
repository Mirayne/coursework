package com.example.demo.view.converters;

import com.example.demo.entities.ContentVendors;
import com.example.demo.view.ContentVendorsDTO;
import org.springframework.stereotype.Service;

@Service
public class ContentVendorsConverter {
    public ContentVendors toContentVendors(ContentVendorsDTO contentVendorsDTO) {
        ContentVendors contentVendors = new ContentVendors();

        contentVendors.setContentVendorId(contentVendorsDTO.getContentVendorId());
        contentVendors.setContentVendorName(contentVendorsDTO.getContentVendorName());

        return contentVendors;
    }

    public ContentVendorsDTO toContentVendorsDTO(ContentVendors contentVendors) {
        ContentVendorsDTO contentVendorsDTO = new ContentVendorsDTO();

        contentVendorsDTO.setContentVendorId(contentVendors.getContentVendorId());
        contentVendorsDTO.setContentVendorName(contentVendors.getContentVendorName());

        return contentVendorsDTO;
    }
}
