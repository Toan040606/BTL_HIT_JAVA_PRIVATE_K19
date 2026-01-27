package root.model.entity.core;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.core.User;
import root.model.enums.TableStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tables")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int seatCount;

    @Column(nullable = false, unique = true)
    private String qrLink;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
