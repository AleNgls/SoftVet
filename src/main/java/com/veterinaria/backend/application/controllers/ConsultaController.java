package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreateConsultaReq;
import com.veterinaria.backend.application.dto.response.ConsultaResponse;
import com.veterinaria.backend.domain.Consulta;
import com.veterinaria.backend.services.interfaces.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> findAll() {
        var consultas = consultaService.findAll()
                .stream().map(ConsultaResponse::from)
                .toList();
        return ResponseHandler.success(consultas);
    }

    // Se usa consumes = MediaType.MULTIPART_FORM_DATA_VALUE para aceptar archivos
    @PostMapping(value = "/paciente/{pacienteId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> create(
            @RequestPart("consulta") CreateConsultaReq consultaReq,
            @RequestPart(value = "pdf", required = false) MultipartFile pdfFile,
            @PathVariable Long pacienteId) {

        Consulta consulta = new Consulta();
        consulta.setMotivo(consultaReq.getMotivo());
        consulta.setAnamnesis(consultaReq.getAnamnesis());
        consulta.setExamenClinico(consultaReq.getExamenClinico());
        consulta.setDiagnostico(consultaReq.getDiagnostico());
        consulta.setPedidoComplementario(consultaReq.getPedidoComplementario());
        consulta.setTratamiento(consultaReq.getTratamiento());
        consulta.setIndicaciones(consultaReq.getIndicaciones());

        // Se envía el archivo adjunto al servicio
        consultaService.create(consulta, pacienteId, pdfFile);

        return ResponseHandler.success("Consulta creada exitosamente");
    }

    // Endpoint adicional para abrir o descargar los archivos adjuntos desde el frontend
    @GetMapping("/archivos/{filename:.+}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Resource> getPdf(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/consultas/").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}