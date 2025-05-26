package com.prueba.devsu.service;

import com.prueba.devsu.dto.ReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ReportDTO> getReport(Long clientId, LocalDate start, LocalDate end);

}
