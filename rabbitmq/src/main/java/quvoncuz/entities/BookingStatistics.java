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
@Table(name = "booking_stats")
@NoArgsConstructor
@AllArgsConstructor
public class BookingStatistics {

    @Id
    private Long id;

    private Long tourId;

    private LocalDateTime createdAt;

}
