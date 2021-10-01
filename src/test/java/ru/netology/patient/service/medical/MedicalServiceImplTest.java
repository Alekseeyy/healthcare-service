package ru.netology.patient.service.medical;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

class MedicalServiceImplTest {

    @Test
    void checkBloodPressureNotNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров",
                LocalDate.of(1980, 11, 26), new HealthInfo(new BigDecimal("36.65"),
                new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BloodPressure bloodPressure = new BloodPressure(60, 120);
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);

        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(sendAlertService).send(message);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkBloodPressure(info.getId(), bloodPressure);

        Mockito.verify(sendAlertService, Mockito.times(1)).send(message);
    }

    @Test
    void checkBloodPressureNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров",
                LocalDate.of(1980, 11, 26), new HealthInfo(new BigDecimal("36.65"),
                new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BloodPressure bloodPressure = new BloodPressure(120, 80);
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);

        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(sendAlertService).send(message);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkBloodPressure(info.getId(), bloodPressure);

        Mockito.verify(sendAlertService, Mockito.times(0)).send(message);
    }

    @Test
    void checkTemperatureNotNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров",
                LocalDate.of(1980, 11, 26), new HealthInfo(new BigDecimal("36.65"),
                new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BigDecimal bigDecimal = new BigDecimal("34.9");
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);

        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(sendAlertService).send(message);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(info.getId(), bigDecimal);

        Mockito.verify(sendAlertService, Mockito.times(1)).send(message);
    }

    @Test
    void checkTemperatureNormal() {
        PatientInfo patientInfo = new PatientInfo("Иван", "Петров",
                LocalDate.of(1980, 11, 26), new HealthInfo(new BigDecimal("36.65"),
                new BloodPressure(120, 80)));
        PatientInfo info = new PatientInfo(UUID.randomUUID().toString(),
                patientInfo.getName(),
                patientInfo.getSurname(),
                patientInfo.getBirthday(),
                patientInfo.getHealthInfo());
        BigDecimal bigDecimal = new BigDecimal("36.6");
        String message = String.format("Warning, patient with id: %s, need help", info.getId());

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(info.getId())).thenReturn(info);

        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        Mockito.doNothing().when(sendAlertService).send(message);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(info.getId(), bigDecimal);

        Mockito.verify(sendAlertService, Mockito.times(0)).send(message);
    }
}