package com.nbisht21.userservice.service;

import com.nbisht21.userservice.VO.Department;
import com.nbisht21.userservice.VO.ResponseTemplateVO;
import com.nbisht21.userservice.entity.User;
import com.nbisht21.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside ResponseTemplateVO of UserService");
        ResponseTemplateVO VO = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" +
                        user.getDepartmentId(), Department.class);

        VO.setUser(user);
        VO.setDepartment(department);

        return VO;
    }
}
