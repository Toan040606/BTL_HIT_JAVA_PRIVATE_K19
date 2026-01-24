package root.model.entity.order.detail;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.menu.FoodAddon;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodAddonItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderItem_id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "foodAddon_id")
    private FoodAddon foodAddon;

    private int quantity;

    private double priceAtOrder;
}
