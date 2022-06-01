package woowacourse.shoppingcart.domain;

import java.util.regex.Pattern;
import woowacourse.shoppingcart.exception.InvalidInputException;

public class Customer {
    private static final Pattern usernamePattern =
            Pattern.compile("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    private static final Pattern passwordPattern =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$");
    private static final Pattern nicknamePattern =
            Pattern.compile("^(?=.*[a-z0-9가-힣ㄱ-ㅎㅏ-ㅣ])[a-z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{2,10}$");

    private final Long id;
    private final String username;
    private final String password;
    private final String nickname;

    public Customer(final Long id,
                    final String username,
                    final String password,
                    final String nickname) {
        validateUsername(username);
        validatePassword(password);
        validateNickname(nickname);
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static Customer ofNullId(final String username,
                                    final String password,
                                    final String nickname) {
        return new Customer(null, username, password, nickname);
    }

    private void validateUsername(final String username) {
        if (!usernamePattern.matcher(username).matches()) {
            throw new InvalidInputException("올바르지 않은 포맷의 아이디 입니다.");
        }
    }

    private void validatePassword(final String password) {
        if (!passwordPattern.matcher(password).matches()) {
            throw new InvalidInputException("올바르지 않은 포맷의 패스워드 입니다.");
        }
    }

    private void validateNickname(final String nickname) {
        if (!nicknamePattern.matcher(nickname).matches()) {
            throw new InvalidInputException("올바르지 않은 포맷의 닉네임 입니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

}
