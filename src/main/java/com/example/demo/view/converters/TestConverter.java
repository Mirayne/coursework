package com.example.demo.view.converters;

import com.example.demo.entities.MedBrigade;
import com.example.demo.view.MedBrigadeDTO;
import com.example.demo.view.TestDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TestConverter {
    public TestDTO testConvert(MedBrigade medBrigade) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(medBrigade.getNumberOfBrigade());
        testDTO.setDriverName(medBrigade.getDriver().getNameOfDriver());
        testDTO.setMedic1Name(medBrigade.getMedic1().getMedicName());
        testDTO.setMedic2Name(medBrigade.getMedic2().getMedicName());
        testDTO.setMedic3Name(medBrigade.getMedic3().getMedicName());
        testDTO.setMedKit(medBrigade.getMedkit().getNumberOfMedkit());
        testDTO.setRegion(medBrigade.getRegion().getRegionBeginAddress() + " - " + medBrigade.getRegion().getRegionEndAddress());
        return testDTO;
    }
}
