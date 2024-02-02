package com.agrotis.trainees.crud.repository;
import java.time.LocalDate; 
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agrotis.trainees.crud.entity.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer>{
	Optional<NotaFiscal> findByDataNota(LocalDate dataNota);
	

}
