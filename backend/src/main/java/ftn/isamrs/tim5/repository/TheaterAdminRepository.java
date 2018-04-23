package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.CineterAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterAdminRepository extends JpaRepository<CineterAdmin, Long> {
}
