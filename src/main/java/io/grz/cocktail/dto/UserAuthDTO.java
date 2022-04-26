package io.grz.cocktail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserAuthDTO {

    @NotBlank(message = "이메일을 작성해주세요")
    @Size(max = 50, message = "이메일 길이를 초과하였습니다")
    private String username;

    @NotBlank(message = "비밀번호를 확인해주세요")
    @Size(max=75, message = "비밀번호 길이를 초과하였습니다")
    private String password;

    @NotNull(message = "no key named nickname")
    @NotBlank(message = "닉네임을 작성해주세요")
    @Size(max = 50, message = "닉네임 길이를 초과하였습니다")
    private String nickname;
    private String role;
    private String provider;
    private String providerId;
    private Timestamp createDate;
    private Timestamp accessDate;

}
