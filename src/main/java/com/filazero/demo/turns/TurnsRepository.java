package com.filazero.demo.turns;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filazero.demo.enums.TurnsStatus;

public interface TurnsRepository extends JpaRepository<TurnsEntity, Long> {

      List<TurnsEntity> findByStartTimeBetweenAndStatus(
        LocalDateTime start,
        LocalDateTime end,
        TurnsStatus status
    );

      Optional<TurnsEntity> findAllByOrderByTimeSlotAsc();


}

