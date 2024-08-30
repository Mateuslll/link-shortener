package tech.mateuslll.urlshortener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.mateuslll.urlshortener.controller.dto.ShortenUrlRequest;
import tech.mateuslll.urlshortener.controller.dto.ShortenUrlResponse;
import tech.mateuslll.urlshortener.entities.UrlEntity;
import tech.mateuslll.urlshortener.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest) {

        String id;
        do {
            id = RandomStringUtils.randomAlphabetic(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusSeconds(60)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        // Retorna a URL encurtada baseada na URL da requisição original
        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> getUrl(@PathVariable String id) {
       var url = urlRepository.findById(id);

       if(url.isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       HttpHeaders headers = new HttpHeaders();
       headers.setLocation(URI.create(url.get().getFullUrl()));
       return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
