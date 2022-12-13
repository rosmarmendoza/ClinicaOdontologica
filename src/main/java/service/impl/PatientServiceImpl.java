package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PatientDTO;

import models.Patient;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import repository.IPatientRepository;
import service.PatientService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class PatientServiceImpl implements PatientService {

    private final IPatientRepository patientRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    private final Logger log= (Logger) LoggerFactory.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDTO save(PatientDTO patientDTO) {
        log.info("Request to save Patient : {}");
        return mapToDto(patientRepository.save(mapToEntity(patientDTO)));
    }

    @Override
    public void delete(PatientDTO patientDTO) {
        log.info("Request to delete Patient : {}");
        patientRepository.delete(mapToEntity(patientDTO));
    }

    @Override
    public PatientDTO getByDni(String dni) {
        log.info("Request to get Patient : {}");
        return mapToDto(patientRepository.findByDni(dni));
    }

    @Override
    public List<PatientDTO> getAll() {
        log.info("Request to get all Patients");
        return patientRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }


    private PatientDTO mapToDto (Patient patient) {
        return mapper.convertValue(patient, PatientDTO.class);
    }

    private Patient mapToEntity (PatientDTO patientDto) {
        return mapper.convertValue(patientDto, Patient.class);
    }

    @Override
    public List<Patient> findAll() {
        return null;
    }

    @Override
    public List<Patient> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Patient> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Patient> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Patient entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Patient> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Patient> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Patient> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Patient> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Patient> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Patient> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Patient> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Patient getOne(Long aLong) {
        return null;
    }

    @Override
    public Patient getById(Long aLong) {
        return null;
    }

    @Override
    public Patient getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Patient> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Patient> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Patient> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Patient> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Patient> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Patient> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Patient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
