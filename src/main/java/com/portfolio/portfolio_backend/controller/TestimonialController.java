package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.entity.Testimonial;
import com.portfolio.portfolio_backend.repository.TestimonialRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
@CrossOrigin(origins = "*")
public class TestimonialController {

    private final TestimonialRepository testimonialRepository;

    public TestimonialController(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    @GetMapping
    public List<Testimonial> getTestimonials() {
        return testimonialRepository.findAll();
    }

    @PostMapping
    public Testimonial createTestimonial(@RequestBody Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }

    @PutMapping("/{id}")
    public Testimonial updateTestimonial(
            @PathVariable Long id,
            @RequestBody Testimonial testimonial) {

        testimonial.setId(id);
        return testimonialRepository.save(testimonial);
    }

    @DeleteMapping("/{id}")
    public void deleteTestimonial(@PathVariable Long id) {
        testimonialRepository.deleteById(id);
    }
}
