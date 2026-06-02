package quvoncuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quvoncuz.entities.BookingStatistics;

import java.time.LocalDateTime;

@Repository
public interface BookingStatsRepository extends JpaRepository<BookingStatistics, Long> {

    @Query("""
            select count(b) as count
            from BookingStatistics b
            where b.createdAt between ?1 and ?2
            """)
    long findCountByGivenDays(LocalDateTime from, LocalDateTime to);

}
