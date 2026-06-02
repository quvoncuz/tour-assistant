package quvoncuz.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "tour_stats")
@NoArgsConstructor
@AllArgsConstructor
public class TourStatistics {

    @Id
    private Long id;

    private Long agencyId;

    private LocalDateTime createdAt;
}
