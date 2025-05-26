package com.prueba.devsu.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReportDTO {
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private Double movimiento;
    private Double saldoDisponible;
}
