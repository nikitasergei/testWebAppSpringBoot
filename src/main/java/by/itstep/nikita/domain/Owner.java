package by.itstep.nikita.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Заполни поле")
    private String name;

    @NotBlank(message = "Заполни поле")
    private String address;

    private boolean isDeleted = false;

}
