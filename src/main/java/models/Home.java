package models;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Home {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private String street;
    private int number;
    private String locality;
    private String province;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Dentist dentist;
}
