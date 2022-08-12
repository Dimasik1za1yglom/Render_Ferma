package example.demo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TaskForm {

    @NotBlank
    @Size(min = 1, max = 80, message = "Поле не должно быть пустым")
    private String name;
}
