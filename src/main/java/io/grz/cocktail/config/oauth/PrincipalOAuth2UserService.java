package io.grz.cocktail.config.oauth;

import io.grz.cocktail.config.auth.PrincipalDetails;
import io.grz.cocktail.config.oauth.provider.FacebookUserInfo;
import io.grz.cocktail.config.oauth.provider.GoogleUserInfo;
import io.grz.cocktail.config.oauth.provider.NaverUserInfo;
import io.grz.cocktail.config.oauth.provider.OAuth2UserInfo;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.UUIDGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo;
        if(Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if(Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "facebook")){
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else if(Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "naver")){
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        } else {
            throw new IllegalArgumentException("oAuth2UserInfo is null");
        }

        User userEntity = userRepository.findByUsername(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId());
        if(userEntity == null){
            userEntity = new User();
            userEntity.setUsername(oAuth2UserInfo.getEmail());
            userEntity.setPassword(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()));
            userEntity.setNickname(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId());
            userEntity.setRole("USER");
            userEntity.setProvider(oAuth2UserInfo.getProvider());
            userEntity.setProviderId(oAuth2UserInfo.getProviderId());
            System.out.println(userEntity);
            userRepository.save(userEntity);
        }
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
