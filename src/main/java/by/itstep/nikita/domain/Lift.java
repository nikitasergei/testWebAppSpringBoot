package by.itstep.nikita.domain;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
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

    @NotBlank(message = "Заполни меня")
    private String ptoDate;

    @NotBlank(message = "Заполни меня")
    private String to2Month;

    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

}
