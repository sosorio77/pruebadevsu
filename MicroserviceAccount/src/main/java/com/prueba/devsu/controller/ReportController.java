package com.prueba.devsu.controller;

import com.prueba.devsu.dto.ReportDTO;
import com.prueba.devsu.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ReportDTO> getReport(@RequestParam Long cliente,
                                     @RequestParam("fechaInicio") String fechaInicio,
                                     @RequestParam("fechaFin") String fechaFin) {
        LocalDate start = LocalDate.parse(fechaInicio);
        LocalDate end = LocalDate.parse(fechaFin);
        return reportService.getReport(cliente, start, end);
    }
}
