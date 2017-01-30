package com.moya.atletamedalla.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.moya.atletamedalla.domain.Medalla;

import com.moya.atletamedalla.repository.MedallaRepository;
import com.moya.atletamedalla.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Medalla.
 */
@RestController
@RequestMapping("/api")
public class MedallaResource {

    private final Logger log = LoggerFactory.getLogger(MedallaResource.class);
        
    @Inject
    private MedallaRepository medallaRepository;

    /**
     * POST  /medallas : Create a new medalla.
     *
     * @param medalla the medalla to create
     * @return the ResponseEntity with status 201 (Created) and with body the new medalla, or with status 400 (Bad Request) if the medalla has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/medallas")
    @Timed
    public ResponseEntity<Medalla> createMedalla(@Valid @RequestBody Medalla medalla) throws URISyntaxException {
        log.debug("REST request to save Medalla : {}", medalla);
        if (medalla.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("medalla", "idexists", "A new medalla cannot already have an ID")).body(null);
        }
        Medalla result = medallaRepository.save(medalla);
        return ResponseEntity.created(new URI("/api/medallas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("medalla", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /medallas : Updates an existing medalla.
     *
     * @param medalla the medalla to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated medalla,
     * or with status 400 (Bad Request) if the medalla is not valid,
     * or with status 500 (Internal Server Error) if the medalla couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/medallas")
    @Timed
    public ResponseEntity<Medalla> updateMedalla(@Valid @RequestBody Medalla medalla) throws URISyntaxException {
        log.debug("REST request to update Medalla : {}", medalla);
        if (medalla.getId() == null) {
            return createMedalla(medalla);
        }
        Medalla result = medallaRepository.save(medalla);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("medalla", medalla.getId().toString()))
            .body(result);
    }

    /**
     * GET  /medallas : get all the medallas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of medallas in body
     */
    @GetMapping("/medallas")
    @Timed
    public List<Medalla> getAllMedallas() {
        log.debug("REST request to get all Medallas");
        List<Medalla> medallas = medallaRepository.findAll();
        return medallas;
    }

    /**
     * GET  /medallas/:id : get the "id" medalla.
     *
     * @param id the id of the medalla to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the medalla, or with status 404 (Not Found)
     */
    @GetMapping("/medallas/{id}")
    @Timed
    public ResponseEntity<Medalla> getMedalla(@PathVariable Long id) {
        log.debug("REST request to get Medalla : {}", id);
        Medalla medalla = medallaRepository.findOne(id);
        return Optional.ofNullable(medalla)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /medallas/:id : delete the "id" medalla.
     *
     * @param id the id of the medalla to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/medallas/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedalla(@PathVariable Long id) {
        log.debug("REST request to delete Medalla : {}", id);
        medallaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("medalla", id.toString())).build();
    }

}
