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
public class OptionRequiredGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean active;

    @ManyToMany
    @JoinTable(name = "Food_ORG",
        joinColumns = {@JoinColumn(name = "ORG_id")},
        inverseJoinColumns = {@JoinColumn(name = "food_id")}
    )
    private List<Food> foods = new ArrayList<>();

    @OneToMany(mappedBy = "optionRequiredGroup")
    private List<OptionRequired> optionsRequired = new ArrayList<>();
}
