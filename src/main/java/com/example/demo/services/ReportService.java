package com.example.demo.services;

import com.example.demo.entities.CallingResults;
import com.example.demo.entities.Callings;
import com.example.demo.entities.SpentOfContent;
import com.example.demo.repo.CallingRepository;
import com.example.demo.repo.CallingResultsRepository;
import com.example.demo.repo.ContentOfKitRepository;
import com.example.demo.repo.SpentOfContentRepository;
import com.example.demo.view.CallingsDTO;
import com.example.demo.view.converters.CallingResultsConverter;
import com.example.demo.view.converters.CallingsConverter;
import com.example.demo.view.converters.ContentOfKitConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final CallingRepository callingRepository;
    private final CallingResultsRepository callingResultsRepository;
    private final ContentOfKitRepository contentOfKitRepository;
    private final SpentOfContentRepository spentOfContentRepository;

    private final CallingsConverter callingsConverter;
    private final CallingResultsConverter callingResultsConverter;
    private final ContentOfKitConverter contentOfKitConverter;

    @Autowired
    private CallingsService callingsService;

    @Autowired
    public ReportService(CallingRepository callingRepository, CallingResultsRepository callingResultsRepository,
                         ContentOfKitRepository contentOfKitRepository, CallingsConverter callingsConverter,
                         CallingResultsConverter callingResultsConverter, ContentOfKitConverter contentOfKitConverter,
                         SpentOfContentRepository spentOfContentRepository) {
        this.callingRepository = callingRepository;
        this.callingResultsRepository = callingResultsRepository;
        this.contentOfKitRepository = contentOfKitRepository;
        this.callingsConverter = callingsConverter;
        this.callingResultsConverter = callingResultsConverter;
        this.contentOfKitConverter = contentOfKitConverter;
        this.spentOfContentRepository = spentOfContentRepository;
    }

    //Список ложных вызовов за определенный месяц
    public Map<Integer, LocalDateTime> mapOfFalseCall(Integer month) {
        return callingsService.findAll().stream().filter(o -> o.getStatusDtoID() == 3)
                .filter(o -> LocalDateTime.parse(o.getCallingDateTime()).getMonth().equals(Month.of(month)))
                .collect(Collectors.toMap(CallingsDTO::getId, callingsDTO -> LocalDateTime.parse(callingsDTO.getCallingDateTime())));
    }

    //Список бригад со средней скоростью их работы за определенный месяц
    public Map<Integer, String> avgOfBrigadeReactTime(Integer month) {
        List<MedBrigadeResults> medBrigadeResultsList = new ArrayList<>();
        List<Callings> callings = callingRepository.findAll();
        List<CallingResults> results = callingResultsRepository.findAll();

        for (Callings call : callings) {
            if (call.getCallingDateTime().getMonth().equals(Month.of(month))) {
                for (CallingResults resultOfCall : results) {
                    if (resultOfCall.getCalling().equals(call)) {
                        Integer numberOfBrigade = call.getMedBrigade().getNumberOfBrigade();
                        LocalDateTime callDateTime = call.getCallingDateTime();
                        LocalDateTime resultDateTime = resultOfCall.getCompletionDate();
                        long resultTimeInSeconds = callDateTime.until(resultDateTime, ChronoUnit.SECONDS);

                        boolean finded = false;
                        for (MedBrigadeResults medBrigadeResults : medBrigadeResultsList) {
                            if (medBrigadeResults.getId().equals(numberOfBrigade)) {
                                medBrigadeResults.setAllTime(medBrigadeResults.getAllTime() + resultTimeInSeconds);
                                medBrigadeResults.sumOfCallings++;
                                finded = true;
                            }
                        }

                        if (!finded) {
                            medBrigadeResultsList.add(new MedBrigadeResults(numberOfBrigade, 1, resultTimeInSeconds));
                        }
                    }
                }
            }
        }
        return medBrigadeResultsList.stream().collect(Collectors.toMap(MedBrigadeResults::getId, MedBrigadeResults::calculateAvgTime));
    }

    public Double sumOfSpent(Integer month){
        Double sum = 0.0;
        for (SpentedContent sc: spentPerMonth(month)){
            sum += sc.getSpentCost();
        }
        return sum;
    }

    public List<SpentedContent> spentPerMonth(Integer month) {
        List<SpentedContent> spentedContentList = new ArrayList<>();
        List<SpentOfContent> spentOfContentList = spentOfContentRepository.findAll();
        boolean finded = false;

        for (SpentOfContent spentOfContent : spentOfContentList) {
            if (spentOfContent.getCallingResult().getCompletionDate().getMonth().equals(Month.of(month))) {
                for (SpentedContent spentedContent : spentedContentList) {
                    if (spentOfContent.getThingsSpent().getContentTitle().equals(spentedContent.getContentTitle())) {
                        spentedContent.setAmount(spentedContent.getAmount() + spentOfContent.getSpentCount());
                        finded = true;
                    }
                }

                if (!finded) {
                    spentedContentList.add(new SpentedContent(spentOfContent.getThingsSpent().getContentTitle(),
                            spentOfContent.getSpentCount()));
                }
            }
        }

        spentedContentList.forEach(SpentedContent::setSpentCost);
        return spentedContentList;
    }

    @Data
    @AllArgsConstructor
    private class MedBrigadeResults {
        private Integer id;
        private Integer sumOfCallings;
        private long allTime;

        private String calculateAvgTime() {
            long avgTime = allTime / sumOfCallings;
            long hours = avgTime / 3600;
            long minutes = (avgTime % 3600) / 60;
            long seconds = avgTime % 60;
            return hours + ":" + minutes + ":" + seconds;
        }
    }


    private class SpentedContent {
        private String contentTitle;
        private Integer amount;
        private Integer spentCost;

        public SpentedContent(String contentTitle, Integer amount) {
            this.contentTitle = contentTitle;
            this.amount = amount;
        }

        public String getContentTitle() {
            return contentTitle;
        }

        public void setContentTitle(String contentTitle) {
            this.contentTitle = contentTitle;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public void setSpentCost() {
            contentOfKitRepository.findAll()
                    .forEach(contentOfKit -> {
                        if (contentOfKit.getContentTitle().equals(contentTitle)) {
                            this.spentCost = contentOfKit.getPricePerContentUnit() * amount;
                        }
                    });
        }

        public Integer getSpentCost() {
            return spentCost;
        }
    }
}
