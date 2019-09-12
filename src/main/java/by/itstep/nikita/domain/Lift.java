package by.itstep.nikita.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Lift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Заполни поле")
    private String address;

    @Pattern(regexp = "29-32-[0-9]{4}")
    private String regNum;

    @Pattern(regexp = "[0-9]{6}")
    private String factNum;

    @Pattern(regexp = "[0-9]{4}")
    private String manufactureYear;

    @Pattern(regexp = "[0-4]")
    private Integer unit;

    @Pattern(regexp = "[1-12]")
    private Integer monthTO2;

    @Pattern(regexp = "[1-31][.][1-12][.20][18<50]")
    private String ptoData;

    private boolean isDeleted = false;


}
