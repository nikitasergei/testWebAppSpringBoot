package by.itstep.nikita.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class TechServiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Заполни меня")
    private String ptoDate;     //This field will be changed on Date

    @NotBlank(message = "Заполни меня")
    private String to2Month;        //This field will be changed on Date

    @NotBlank(message = "Заполни меня")
    private String to1Date;        //This field will be changed on Date

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lift_id")
    private Lift lift;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    boolean isDone = false;         //Add to ftl possibility to change field
}
