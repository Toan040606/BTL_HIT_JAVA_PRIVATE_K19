package root.model.entity.order.detail;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.menu.OptionRequired;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionRequiredItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "optionRequired_id")
    private OptionRequired optionRequired;

    @ManyToOne
    @JoinColumn(name = "orderItem_id")
    private OrderItem orderItem;

    private double priceAtOrder;
}
