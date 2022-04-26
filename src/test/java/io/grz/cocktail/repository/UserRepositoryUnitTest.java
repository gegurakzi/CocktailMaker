package io.grz.cocktail.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

// @AutoConfigureTestDatabase = 테스트에 사용할 데이터베이스 설정
// Replace.ANY = 가짜 DB로 테스트
// Replace.NONE = 실제 DB로 테스트

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest
public class UserRepositoryUnitTest {

    @Autowired
    private UserRepository userRepository;

}
