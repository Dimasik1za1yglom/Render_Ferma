package example.demo.service;

import example.demo.dao.AccountRepository;
import example.demo.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean accountVerification(String login) {
        String loginNotEmpty = login.strip();
        return accountRepository.findAccountsByLogin(loginNotEmpty).isPresent();
    }

    @Override
    public boolean addAccount(String login, String password) {
        String loginNotEmpty = login.strip();
        String passwordNotEmpty = password.strip();
        var account = Account.builder()
                .login(loginNotEmpty)
                .password(passwordEncoder.encode(passwordNotEmpty))
                .build();
        try {
            accountRepository.save(account);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
