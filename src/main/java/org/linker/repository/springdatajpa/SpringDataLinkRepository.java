package org.linker.repository.springdatajpa;

import org.linker.model.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataLinkRepository extends JpaRepository<Link, Integer> {
}
