package com.nbu.gradebook.configuration;

import com.nbu.gradebook.commons.utilities.Mapper;
import com.nbu.gradebook.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.annotation.WebServlet;

@Configuration
public class BeanConfig {

    @Bean(name = "modelMapper")
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "mapper")
    public Mapper mapper(ModelMapper modelMapper) {
        return new Mapper(modelMapper);
    }

    @Bean(name = "BCryptPasswordEncoder")
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "baseUserService")
    public BaseUserService baseUserServiceBean() { return new BaseUserServiceImpl(); }

    @Bean(name = "userService")
    public UserService userServiceBean() {
        return new UserServiceImpl();
    }

    @Bean(name = "roleService")
    public RoleService roleServiceBean() {
        return new RoleServiceImpl();
    }

    @Bean(name = "schoolService")
    public SchoolService schoolServiceBean() {
        return new SchoolServiceImpl();
    }

    @Bean(name = "teacherService")
    public TeacherService teacherServiceBean() {
        return new TeacherServiceImpl();
    }

    @Bean(name = "directorService")
    public DirectorService directorServiceBean() {
        return new DirectorServiceImpl();
    }

    @Bean(name = "studentService")
    public StudentService studentServiceBean() {
        return new StudentServiceImpl();
    }

    @Bean(name = "classService")
    public ClassService classServiceBean() {
        return new ClassServiceImpl();
    }

    @Bean(name = "subjectService")
    public SubjectService subjectServiceBean() { return new SubjectServiceImpl(); }

}
