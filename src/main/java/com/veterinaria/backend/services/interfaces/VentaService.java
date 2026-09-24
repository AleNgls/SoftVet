package com.veterinaria.backend.services.interfaces;

import com.veterinaria.backend.application.dto.request.CreateVentaReq;
import com.veterinaria.backend.domain.Venta;

import java.util.List;

public interface VentaService {

    void create(CreateVentaReq createVentaReq);

    List<Venta> findAll();

}
