package com.tools.potato_field.item;

import com.tools.potato_field.exception.ItemNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item findItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Page<Item> findAllItems(Pageable pageable) { // 페이징 처리
        return itemRepository.findAll(pageable);
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }

    public List<Item> findItemsByPostId(Long postId) {
        return itemRepository.findByPostId(postId);
    }
}
