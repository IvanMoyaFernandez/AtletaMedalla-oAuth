package com.moya.atletamedalla.web.rest;

import com.moya.atletamedalla.AtletaMedallaApp;

import com.moya.atletamedalla.domain.Medalla;
import com.moya.atletamedalla.repository.MedallaRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.moya.atletamedalla.domain.enumeration.TipoMedalla;
/**
 * Test class for the MedallaResource REST controller.
 *
 * @see MedallaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AtletaMedallaApp.class)
public class MedallaResourceIntTest {

    private static final TipoMedalla DEFAULT_TIPO_MEDALLA = TipoMedalla.ORO;
    private static final TipoMedalla UPDATED_TIPO_MEDALLA = TipoMedalla.PLATA;

    private static final String DEFAULT_ESPECIALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_ESPECIALIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_COMPETICION = "AAAAAAAAAA";
    private static final String UPDATED_COMPETICION = "BBBBBBBBBB";

    @Inject
    private MedallaRepository medallaRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restMedallaMockMvc;

    private Medalla medalla;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MedallaResource medallaResource = new MedallaResource();
        ReflectionTestUtils.setField(medallaResource, "medallaRepository", medallaRepository);
        this.restMedallaMockMvc = MockMvcBuilders.standaloneSetup(medallaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medalla createEntity(EntityManager em) {
        Medalla medalla = new Medalla()
                .tipoMedalla(DEFAULT_TIPO_MEDALLA)
                .especialidad(DEFAULT_ESPECIALIDAD)
                .competicion(DEFAULT_COMPETICION);
        return medalla;
    }

    @Before
    public void initTest() {
        medalla = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedalla() throws Exception {
        int databaseSizeBeforeCreate = medallaRepository.findAll().size();

        // Create the Medalla

        restMedallaMockMvc.perform(post("/api/medallas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medalla)))
            .andExpect(status().isCreated());

        // Validate the Medalla in the database
        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeCreate + 1);
        Medalla testMedalla = medallaList.get(medallaList.size() - 1);
        assertThat(testMedalla.getTipoMedalla()).isEqualTo(DEFAULT_TIPO_MEDALLA);
        assertThat(testMedalla.getEspecialidad()).isEqualTo(DEFAULT_ESPECIALIDAD);
        assertThat(testMedalla.getCompeticion()).isEqualTo(DEFAULT_COMPETICION);
    }

    @Test
    @Transactional
    public void createMedallaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medallaRepository.findAll().size();

        // Create the Medalla with an existing ID
        Medalla existingMedalla = new Medalla();
        existingMedalla.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedallaMockMvc.perform(post("/api/medallas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMedalla)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoMedallaIsRequired() throws Exception {
        int databaseSizeBeforeTest = medallaRepository.findAll().size();
        // set the field null
        medalla.setTipoMedalla(null);

        // Create the Medalla, which fails.

        restMedallaMockMvc.perform(post("/api/medallas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medalla)))
            .andExpect(status().isBadRequest());

        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEspecialidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = medallaRepository.findAll().size();
        // set the field null
        medalla.setEspecialidad(null);

        // Create the Medalla, which fails.

        restMedallaMockMvc.perform(post("/api/medallas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medalla)))
            .andExpect(status().isBadRequest());

        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompeticionIsRequired() throws Exception {
        int databaseSizeBeforeTest = medallaRepository.findAll().size();
        // set the field null
        medalla.setCompeticion(null);

        // Create the Medalla, which fails.

        restMedallaMockMvc.perform(post("/api/medallas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medalla)))
            .andExpect(status().isBadRequest());

        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedallas() throws Exception {
        // Initialize the database
        medallaRepository.saveAndFlush(medalla);

        // Get all the medallaList
        restMedallaMockMvc.perform(get("/api/medallas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medalla.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoMedalla").value(hasItem(DEFAULT_TIPO_MEDALLA.toString())))
            .andExpect(jsonPath("$.[*].especialidad").value(hasItem(DEFAULT_ESPECIALIDAD.toString())))
            .andExpect(jsonPath("$.[*].competicion").value(hasItem(DEFAULT_COMPETICION.toString())));
    }

    @Test
    @Transactional
    public void getMedalla() throws Exception {
        // Initialize the database
        medallaRepository.saveAndFlush(medalla);

        // Get the medalla
        restMedallaMockMvc.perform(get("/api/medallas/{id}", medalla.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(medalla.getId().intValue()))
            .andExpect(jsonPath("$.tipoMedalla").value(DEFAULT_TIPO_MEDALLA.toString()))
            .andExpect(jsonPath("$.especialidad").value(DEFAULT_ESPECIALIDAD.toString()))
            .andExpect(jsonPath("$.competicion").value(DEFAULT_COMPETICION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMedalla() throws Exception {
        // Get the medalla
        restMedallaMockMvc.perform(get("/api/medallas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedalla() throws Exception {
        // Initialize the database
        medallaRepository.saveAndFlush(medalla);
        int databaseSizeBeforeUpdate = medallaRepository.findAll().size();

        // Update the medalla
        Medalla updatedMedalla = medallaRepository.findOne(medalla.getId());
        updatedMedalla
                .tipoMedalla(UPDATED_TIPO_MEDALLA)
                .especialidad(UPDATED_ESPECIALIDAD)
                .competicion(UPDATED_COMPETICION);

        restMedallaMockMvc.perform(put("/api/medallas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMedalla)))
            .andExpect(status().isOk());

        // Validate the Medalla in the database
        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeUpdate);
        Medalla testMedalla = medallaList.get(medallaList.size() - 1);
        assertThat(testMedalla.getTipoMedalla()).isEqualTo(UPDATED_TIPO_MEDALLA);
        assertThat(testMedalla.getEspecialidad()).isEqualTo(UPDATED_ESPECIALIDAD);
        assertThat(testMedalla.getCompeticion()).isEqualTo(UPDATED_COMPETICION);
    }

    @Test
    @Transactional
    public void updateNonExistingMedalla() throws Exception {
        int databaseSizeBeforeUpdate = medallaRepository.findAll().size();

        // Create the Medalla

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMedallaMockMvc.perform(put("/api/medallas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(medalla)))
            .andExpect(status().isCreated());

        // Validate the Medalla in the database
        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMedalla() throws Exception {
        // Initialize the database
        medallaRepository.saveAndFlush(medalla);
        int databaseSizeBeforeDelete = medallaRepository.findAll().size();

        // Get the medalla
        restMedallaMockMvc.perform(delete("/api/medallas/{id}", medalla.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Medalla> medallaList = medallaRepository.findAll();
        assertThat(medallaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
