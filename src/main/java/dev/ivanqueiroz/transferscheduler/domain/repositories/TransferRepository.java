package dev.ivanqueiroz.transferscheduler.domain.repositories;

import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
