package br.com.java.foodstadiumapi.repository;

import br.com.java.foodstadiumapi.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
