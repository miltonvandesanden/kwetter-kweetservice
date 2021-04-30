package fhict.kwetter.kweetservice.repository;

import fhict.kwetter.kweetservice.model.Kweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KweetRepository extends JpaRepository<Kweet, Long>
{
}
