package quvoncuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quvoncuz.entities.AgencyStatistics;

import java.time.LocalDateTime;

@Repository
public interface AgencyStatsRepository extends JpaRepository<AgencyStatistics, Long> {

    @Query("""
            select count(a) as count
            from AgencyStatistics a
            where a.createdAt between ?1 and ?2
            """)
    long findCountByGivenDays(LocalDateTime from, LocalDateTime to);

}
