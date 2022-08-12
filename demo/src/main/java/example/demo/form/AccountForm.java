package example.demo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AccountForm {

    @NotBlank()
    @Size(min = 8, max = 100, message = "Неверно введено поле login.")
    private String login;

    @NotBlank()
    @Size(min = 1, max = 100, message = "Неверно введено поле password.")
    private String password;
}
