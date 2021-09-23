package ru.restaurant.vote.to;

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
public class UserDto extends NamedTo implements HasIdAndEmail {
    @Email
    @NotBlank
    @Size(max = 100)
    @NoHtml
    String email;

    public UserDto(Integer id, String name, String email) {
        super(id, name);
        this.email = email;
    }
}