package quvoncuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quvoncuz.entities.TourStatistics;

import java.time.LocalDateTime;

@Repository
public interface TourStatsRepository extends JpaRepository<TourStatistics, Long> {

    @Query("""
            select count(t) as count
            from TourStatistics t
            where t.createdAt between ?1 and ?2
            """)
    long findCountByGivenDays(LocalDateTime from, LocalDateTime to);

}
