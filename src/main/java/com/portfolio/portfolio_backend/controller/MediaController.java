
package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.entity.Media;
import com.portfolio.portfolio_backend.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class MediaController {

    private final MediaRepository mediaRepository;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-key}")
    private String supabaseServiceKey;

    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Media uploadFile(@RequestParam("file") MultipartFile file)
            throws IOException, InterruptedException {

        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || originalFileName.isBlank()) {
            throw new RuntimeException("File name is missing");
        }

        String extension = "";

        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            extension = originalFileName.substring(dotIndex);
        }

        String fileName = UUID.randomUUID() + extension;

        String filePath = "uploads/" + fileName;

        String uploadUrl =
                supabaseUrl +
                "/storage/v1/object/portfolio-media/" +
                filePath;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uploadUrl))
                .header("Authorization", "Bearer " + supabaseServiceKey)
                .header("apikey", supabaseServiceKey)
                .header(
                        "Content-Type",
                        file.getContentType() != null
                                ? file.getContentType()
                                : "application/octet-stream"
                )
                .POST(
                        HttpRequest.BodyPublishers.ofByteArray(
                                file.getBytes()
                        )
                )
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            throw new RuntimeException(
                    "Supabase upload failed: " + response.body()
            );
        }

        String publicUrl =
                supabaseUrl +
                "/storage/v1/object/public/portfolio-media/" +
                filePath;

        Media media = new Media();

        media.setFileName(originalFileName);
        media.setFileUrl(publicUrl);
        media.setFileType(file.getContentType());
        media.setFileSize(file.getSize());
        media.setCreatedAt(LocalDateTime.now());

        return mediaRepository.save(media);
    }
    @GetMapping
        public List<Media> getMedia() {
        return mediaRepository.findAll();
    }
    @DeleteMapping("/{id}")
        public void deleteMedia(@PathVariable Long id) {
        mediaRepository.deleteById(id);
}
}
