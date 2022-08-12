package example.demo.service;


public interface AccountService {
    /**
     * проверка на существование пользователя
     * @param login логин пользователя
     * @return true пользователь существует
     */
    Boolean accountVerification(String login);

    /**
     * добавление пользователя
     * @param login логин пользователя
     * @param password пароль
     * @return true если удалось
     */
    boolean addAccount(String login, String password);



}
