package com.veterinaria.backend.services;

import com.veterinaria.backend.domain.Consulta;
import com.veterinaria.backend.domain.Paciente;
import com.veterinaria.backend.repositories.ConsultaRep;
import com.veterinaria.backend.repositories.PacienteRep;
import com.veterinaria.backend.services.interfaces.ConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRep consultaRep;
    private final PacienteRep pacienteRep;

    private final String UPLOAD_DIR = "uploads/consultas/";

    @Override
    public List<Consulta> findAll() {
        return consultaRep.findAll();
    }

    @Override
    @Transactional
    public Consulta create(Consulta consulta, Long pacienteId, MultipartFile pdfFile) {
        Paciente paciente = pacienteRep.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el ID: " + pacienteId));
        consulta.setPaciente(paciente);
        consulta.setFechaConsulta(LocalDateTime.now());

        // Lógica para guardar el archivo PDF si fue enviado
        if (pdfFile != null && !pdfFile.isEmpty()) {
            try {
                File dir = new File(UPLOAD_DIR);
                if (!dir.exists()) {
                    dir.mkdirs(); // Crea las carpetas si no existen
                }

                // Generar un nombre único con UUID para evitar sobrescribir archivos con el mismo nombre
                String fileName = UUID.randomUUID() + "_" + pdfFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                Files.copy(pdfFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Guardar solo el nombre del archivo en el campo de la base de datos
                consulta.setPdfPath(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar el archivo PDF adjunto", e);
            }
        }

        return consultaRep.save(consulta);
    }
}
