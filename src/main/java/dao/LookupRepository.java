package dao;

import entity.LookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LookupRepository extends JpaRepository<LookupEntity, Long> {
    public List<LookupEntity> findByType(String type);
}
