package cn.os.myframe.repository;

import cn.os.myframe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByAccount(String account);
}
