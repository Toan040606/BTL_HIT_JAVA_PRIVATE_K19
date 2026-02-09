package root.model.entity.menu;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.core.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int sortOrder;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category")
    private List<Food> foods = new ArrayList<>();
}
