package wooteco.subway.member.domain;

import org.apache.commons.lang3.StringUtils;
import wooteco.subway.auth.exception.AuthorizationException;
import wooteco.subway.exception.SubwayCustomException;
import wooteco.subway.member.exception.MemberException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Member {

    private static final Pattern PATTERN =
            Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");

    private Long id;
    private String email;
    private String password;
    private Integer age;

    public Member() {
    }

    public Member(Long id, String email, String password, Integer age) {
        validate(email, password, age);
        this.id = id;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    private void validate(String email, String password, Integer age) {
        validateEmail(email);
        validatePassword(password);
        validateAge(age);
    }

    private void validateEmail(String email) {
        if(Objects.isNull(email) || !isEmailPattern(email)) {
            throw new SubwayCustomException(MemberException.INVALID_EMAIL);
        }
    }

    private boolean isEmailPattern(String email) {
        Matcher m = PATTERN.matcher(email);
        return m.matches();
    }

    private void validatePassword(String password) {
    }

    private void validateAge(int age) {
    }

    public Member(Long id, String email, Integer age) {
        this(id, email, "", age);
    }

    public Member(String email, String password, Integer age) {
        this(0L, email, password, age);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public void checkPassword(String password) {
        if (!StringUtils.equals(this.password, password)) {
            throw new AuthorizationException();
        }
    }
}
