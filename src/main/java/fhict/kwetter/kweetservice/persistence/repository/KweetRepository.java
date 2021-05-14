package fhict.kwetter.kweetservice.persistence.repository;

import fhict.kwetter.kweetservice.persistence.model.Kweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KweetRepository extends JpaRepository<Kweet, Long>
{
}
