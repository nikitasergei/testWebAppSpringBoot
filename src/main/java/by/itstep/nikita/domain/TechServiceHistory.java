package by.itstep.nikita.domain;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class TechServiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Заполни меня")
    private String ptoDate;

    @NotBlank(message = "Заполни меня")
    private String to2Month;

    @NotBlank(message = "Заполни меня")
    private String to1Date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lift_id")
    private Lift lift;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    boolean isDone = false;
}
