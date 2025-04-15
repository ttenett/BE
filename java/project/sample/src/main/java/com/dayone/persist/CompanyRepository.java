package com.dayone.persist;

import com.dayone.persist.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 상속받아서 사용, 제네릭 <엔티티, 엔티티의 id타입>
@Repository
public class CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
