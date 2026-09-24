package com.veterinaria.backend.application.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateVentaReq {

    private List<CreateDetalleDeVentaReq> detalles;

}
