package root.model.entity.menu;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FoodCategory category;

    private String imgPath;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    private boolean active;

    @ManyToMany(mappedBy = "foods")
    private List<FoodAddon> foodAddons = new ArrayList<>();

    @ManyToMany(mappedBy = "foods")
    private List<OptionRequiredGroup> optionRequiredGroups = new ArrayList<>();
}
