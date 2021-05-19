package cn.os.test.repository;

import cn.os.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByAccount(String account);
}
