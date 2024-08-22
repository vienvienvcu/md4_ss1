package ra.test_prj.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column( unique = true, length = 50)
    private String studentName;


    private Integer studentAge;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date studentBirthday;

    @Column
    private Boolean status;

}
