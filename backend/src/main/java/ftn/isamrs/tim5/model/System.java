package ftn.isamrs.tim5.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "system")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    @ElementCollection(targetClass=Integer.class)
    private List<Integer> scale;

    public System() {
        scale = new ArrayList<>();
        Collections.addAll(scale, 0, 20, 40, 60);
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public System(List<Integer> scale) {
        this.scale = scale;
    }

    public List<Integer> getScale() {
        return scale;
    }

    public void setScale(List<Integer> scale) {
        this.scale = scale;
    }
}
