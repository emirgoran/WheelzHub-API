package com.wheelzhub.demo.user;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if (username == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("username"), username);
        };
    }
}
