package Prototype_Backend.prototype.repositories;

import Prototype_Backend.prototype.entities.Laboratoire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboRepository extends JpaRepository<Laboratoire, Long> {

}
