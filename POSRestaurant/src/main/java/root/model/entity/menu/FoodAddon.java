package root.model.entity.menu;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodAddon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double price;

    private boolean active;

    @ManyToMany
    @JoinTable(name = "Food_FoodAddon",
        joinColumns = {@JoinColumn(name = "foodAddon_id")},
        inverseJoinColumns = {@JoinColumn(name = "food_id")}
    )
    private List<Food> foods = new ArrayList<>();
}
