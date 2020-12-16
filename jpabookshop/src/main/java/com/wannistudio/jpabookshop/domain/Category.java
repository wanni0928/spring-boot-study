package com.wannistudio.jpabookshop.domain;

import com.wannistudio.jpabookshop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    ) // 다대다 테이블 관계에서 중간 다리 역할을 하는 테이블에 컬럼 추가를 할 수 없다. 때문에 다대다는 실무에서도 지양하는 방식이다.
    private List<Item> items = new ArrayList<>();

    /*셀프 연관관계*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /*연관관계 메소드드*/
   public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
