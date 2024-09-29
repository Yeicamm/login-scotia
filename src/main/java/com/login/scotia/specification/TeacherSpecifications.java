package com.login.scotia.specification;

import com.login.scotia.entity.Teacher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherSpecifications {
    public static Specification<Teacher> byIdentification(String identification) {
        return (root, query, cb) -> cb.equal(root.get("identification"), identification);
    }
}
