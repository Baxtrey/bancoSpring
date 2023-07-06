package com.banco.bancobackend.repository;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.banco.bancobackend.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    public ArrayList <Transferencia> findByOrdenanteId(Integer ordenante);

    public ArrayList <Transferencia> findByBeneficiarioId(Integer beneficiario);

    public List<Transferencia> findAllByOrdenanteIdAndBeneficiarioId(Integer ordenante, Integer beneficiario);

}
