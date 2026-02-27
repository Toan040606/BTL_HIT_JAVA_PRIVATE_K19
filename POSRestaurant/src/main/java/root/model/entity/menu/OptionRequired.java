package root.model.entity.menu;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.order.detail.OptionRequiredItem;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionRequired {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double price;

    @ManyToOne
    @JoinColumn(name = "optionRequiredGroup_id")
    private OptionRequiredGroup optionRequiredGroup;

    @OneToMany(mappedBy = "optionRequired")
    private List<OptionRequiredItem> optionRequiredItems = new ArrayList<>();
}
