package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.entity.PortfolioService;
import com.portfolio.portfolio_backend.repository.PortfolioServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class PortfolioServiceController {

    private final PortfolioServiceRepository serviceRepository;

    public PortfolioServiceController(PortfolioServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public List<PortfolioService> getServices() {
        return serviceRepository.findAll();
    }

    @PostMapping
    public PortfolioService createService(@RequestBody PortfolioService service) {
        return serviceRepository.save(service);
    }

    @PutMapping("/{id}")
    public PortfolioService updateService(
            @PathVariable Long id,
            @RequestBody PortfolioService service) {

        service.setId(id);
        return serviceRepository.save(service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }
}
