package com.dayone.persist;

import com.dayone.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DividendRepository extends JpaRepository<DividendEntity, Long> {
}
