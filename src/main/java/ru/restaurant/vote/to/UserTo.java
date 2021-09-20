package ru.restaurant.vote.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.restaurant.vote.HasIdAndEmail;
import ru.restaurant.vote.util.validation.NoHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserTo extends NamedTo implements HasIdAndEmail {
    @Email
    @NotBlank
    @Size(max = 100)
    @NoHtml
    String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 5, max = 32)
    String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }
}
