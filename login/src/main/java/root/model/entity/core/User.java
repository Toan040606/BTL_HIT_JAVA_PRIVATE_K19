package root.model.entity.core;

import jakarta.persistence.*;
import lombok.*;
import root.model.entity.core.Order;
import root.model.entity.core.TableEntity;
import root.model.entity.menu.FoodCategory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<FoodCategory> foodCategories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TableEntity> tables = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
