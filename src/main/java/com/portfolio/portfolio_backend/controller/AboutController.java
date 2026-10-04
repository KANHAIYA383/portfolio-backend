package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.entity.About;
import com.portfolio.portfolio_backend.repository.AboutRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "*")
public class AboutController {

    private final AboutRepository aboutRepository;

    public AboutController(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    @GetMapping
    public List<About> getAbout() {
        return aboutRepository.findAll();
    }

    @PostMapping
    public About createAbout(@RequestBody About about) {
        return aboutRepository.save(about);
    }

    @PutMapping("/{id}")
    public About updateAbout(@PathVariable Long id, @RequestBody About about) {
        about.setId(id);
        return aboutRepository.save(about);
    }

    @DeleteMapping("/{id}")
    public void deleteAbout(@PathVariable Long id) {
        aboutRepository.deleteById(id);
    }
}
