package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;    // 주문 상태
}
