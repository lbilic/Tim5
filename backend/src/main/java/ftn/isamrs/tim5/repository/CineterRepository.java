package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CineterRepository extends JpaRepository<Cineter, Long> {

    Cineter findCineterByCityAndAddressAndName(CineterCreateDTO dto);
}
