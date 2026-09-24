package com.veterinaria.backend.services;

import com.veterinaria.backend.application.dto.request.CreateDetalleDeVentaReq;
import com.veterinaria.backend.application.dto.request.CreateVentaReq;
import com.veterinaria.backend.domain.DetalleDeVenta;
import com.veterinaria.backend.domain.Producto;
import com.veterinaria.backend.domain.Venta;
import com.veterinaria.backend.repositories.ProductoRep;
import com.veterinaria.backend.repositories.VentaRep;
import com.veterinaria.backend.services.interfaces.VentaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRep ventaRep;
    private final ProductoRep productoRep;

    @Override
    @Transactional
    public void create(CreateVentaReq ventaRequestDTO) {
        Venta venta = new Venta();

        for (CreateDetalleDeVentaReq detalleDTO : ventaRequestDTO.getDetalles()) {
            Producto producto = productoRep.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleDTO.getProductoId()));

            DetalleDeVenta detalle = new DetalleDeVenta();
            detalle.setProductoId(producto.getId());
            detalle.setNombreProducto(producto.getNombre());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setCantidad(detalleDTO.getCantidad());

            // Asocia el detalle y actualiza la relación bidireccional
            venta.addDetalle(detalle);
        }

        ventaRep.save(venta);
    }

    @Override
    public List<Venta> findAll() {
        return ventaRep.findAll();
    }
}
