package com.wannistudio.jpabookshop.service;

import com.wannistudio.jpabookshop.domain.Address;
import com.wannistudio.jpabookshop.domain.Member;
import com.wannistudio.jpabookshop.domain.OrderStatus;
import com.wannistudio.jpabookshop.domain.item.Book;
import com.wannistudio.jpabookshop.domain.item.Item;
import com.wannistudio.jpabookshop.exception.NotEnoughStockException;
import com.wannistudio.jpabookshop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    void orderItem() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        assertThat(orderRepository.findOne(orderId).getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(orderRepository.findOne(orderId).getOrderItems().size()).isSameAs(1);
        assertThat(item.getStockQuantity()).isSameAs(10 - orderCount);
    }


    @Test
    void exceedStock() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);

        //when
        int orderCnt = 11;

        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), item.getId(), orderCnt));
    }

    @Test
    void cancel() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("Spring", 10000, 10);

        int orderCnt = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCnt);

        //when
        orderService.cancelOrder(orderId);

        //then
        assertThat(orderRepository.findOne(orderId).getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isSameAs(10);
    }

    private Book createBook(String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("wanni");
        member.setAddress(new Address("서울", "양재천", "123-123"));
        em.persist(member);
        return member;
    }
}