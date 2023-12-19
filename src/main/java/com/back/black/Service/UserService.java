package com.back.black.Service;

import com.back.black.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

     User findByUsername(String username);
     User findByUid(int uid);
     void updatePassword(String Password,String username);
     List<User> findAllUser();
     void insertUser(User user);

}
