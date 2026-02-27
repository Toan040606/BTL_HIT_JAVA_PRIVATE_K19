package root.model.entity.order.detail;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.core.Order;
import root.model.entity.core.TableEntity;
import root.model.enums.TableOrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TableOrderStatus status;

    private LocalDateTime openedAt;

    private LocalDateTime closedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @OneToMany(mappedBy = "tableOrder")
    private List<Order> orders = new ArrayList<>();
}
