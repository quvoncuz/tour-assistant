package quvoncuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quvoncuz.entities.UserStatistics;

import java.time.LocalDateTime;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStatistics, Long> {

    @Query("""
            select count(u) as count
            from UserStatistics u
            where u.createdAt between ?1 and ?2
            """)
    long findCountByGivenDays(LocalDateTime from, LocalDateTime to);
}
