package entity;

import javax.persistence.*;

@Entity
@Table(name="student")
public class StudentEntity {
    public StudentEntity() {
    }
    public StudentEntity(Long id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {this.email = email;}

    public Object orElse(Object o) { return  o;}
}
