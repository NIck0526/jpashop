package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional/*readonly false*/
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        /*주입 값이 많으면 updateDto를 만들어서 깔끔하게 넘기기*/

        Item findItem = itemRepository.findOne(itemId); /* 변경감지 Dirty Checking*/

        /*이렇게 풀어놓으면 어디서 변경되는지 찾기 어려움.*/
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);


        /*
        * 이렇게 만들어야지 유지보수가 간편함
        * findItem.change(name, price, stockQuantity)
        * */

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
