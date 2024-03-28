package com.example.ecommerce.item;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository)
    {
        this.itemRepository=itemRepository;
    }

    public List<Item> getItems()
    {
        return itemRepository.findAll();
    }

    public Optional<Item> getItem(long id)
    {
        return  itemRepository.findById(id);
    }

    public void addNewItem(Item item)
    {
        if(itemRepository.existsById(item.getCode()))
        {
           throw new IllegalStateException("item code already in use");
        }
        itemRepository.save((item));

    }

    public void deleteItem(long code) {
        if (!itemRepository.existsById(code)) {
            throw new IllegalStateException("item does not exists");
        }
        itemRepository.deleteById(code);
    }

    public void updateItem(long code,Item updatedItem){
        Item item=itemRepository.findById(code)
                .orElseThrow(()->new EntityNotFoundException("Item not found with code: "+code));
        item.setPrice(updatedItem.getPrice());
        item.setDescription(updatedItem.getDescription());
        item.setImage(updatedItem.getImage());
        item.setInventoryStatus(item.getInventoryStatus());
    }



}
