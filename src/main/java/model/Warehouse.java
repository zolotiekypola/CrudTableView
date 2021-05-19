package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse {
    public Warehouse(String adres) {
        this.adres = adres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String adres;

    @OneToMany(mappedBy = "warehouse")
    private Set<Product> products;

    @OneToMany(mappedBy = "warehouse")
    private Set<Category> categories;

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", adres='" + adres + '\'' +
                '}';
    }
}
