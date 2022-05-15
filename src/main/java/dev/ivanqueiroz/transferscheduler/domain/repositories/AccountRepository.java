package dev.ivanqueiroz.transferscheduler.domain.repositories;

import dev.ivanqueiroz.transferscheduler.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
