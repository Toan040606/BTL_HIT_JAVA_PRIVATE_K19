package root.model.entity.order.detail;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.menu.Food;
import root.model.entity.core.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    private int quantity;

    private BigDecimal priceAtOrder;


    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
    private List<FoodAddonItem> addons = new ArrayList<>();

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
    private List<OptionRequiredItem> options = new ArrayList<>();
}
