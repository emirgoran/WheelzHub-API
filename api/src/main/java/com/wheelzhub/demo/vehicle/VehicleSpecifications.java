package com.wheelzhub.demo.vehicle;

import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecifications {
    public static Specification<Vehicle> hasMake(String make) {
        return (root, query, criteriaBuilder) -> {
            if (make == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("make"), make);
        };
    }

    public static Specification<Vehicle> hasModel(String model) {
        return (root, query, criteriaBuilder) -> {
            if (model == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("model"), model);
        };
    }

    public static Specification<Vehicle> hasLicensePlate(String licensePlate) {
        return (root, query, criteriaBuilder) -> {
            if (licensePlate == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("licensePlate"), licensePlate);
        };
    }
}
