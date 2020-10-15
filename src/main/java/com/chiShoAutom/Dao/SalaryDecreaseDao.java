package com.chiShoAutom.Dao;

import com.chiShoAutom.Models.SalaryDecrease;
import com.chiShoAutom.Models.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface SalaryDecreaseDao<T extends SalaryDecrease> extends JpaRepository<T, Long> {

    List<T> findAllByWorker(Worker worker);

    Page<T> findAllByWorker(Worker worker, Pageable pageable);

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    List<T> findByCreationDate(LocalDate localDate);

}
