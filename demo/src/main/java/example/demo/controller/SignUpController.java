package example.demo.controller;

import example.demo.form.AccountForm;
import example.demo.security.service.AuthService;
import example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping
    public String signUp() {
        return "signUp";
    }

    @PostMapping
    public String signUp(RedirectAttributes redirectAttributes, @Valid AccountForm accountForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getAllErrors().get(0).getDefaultMessage();
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/signup";
        } else if (accountService.accountVerification(accountForm.getLogin())) {
            String error = "Пользователь с таким e-mail уже существует, попробуйте войти в систему";
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/signup";
        } else if (accountService.addAccount(accountForm.getLogin(), accountForm.getPassword())) {
            authService.setUpSecurity(accountForm.getLogin(), accountForm.getPassword());
            return "redirect:/task";
        } else {
            String error = "Не удалось зарегистрировать пользователя, попробуйте позднее";
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/signup";
        }
    }
}
