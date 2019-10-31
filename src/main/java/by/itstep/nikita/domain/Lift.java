package by.itstep.nikita.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@Entity
@Scope("prototype")
public class Lift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Заполни поле")
    @Pattern(regexp = "[A-Z][a-z]*")
    private String city;

    @NotBlank(message = "Заполни поле")
    private String address;

    @Pattern(regexp = "29-32-[0-9]{4}")
    private String regNum;

    @NotBlank(message = "Заполни меня")
    private String factNum;

    @NotBlank(message = "Заполни меня")
    private String activationDate;

    private boolean isDeleted = false;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "lift")
    Set<TechServiceHistory> serviceHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private District district;

}
