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

    public Optional<Item> getItem(String id)
    {
        return  itemRepository.findByCode(id);
    }

    public void addNewItem(Item item)
    {
        if(itemRepository.findByCode(item.getCode()).isPresent())
        {
           throw new IllegalStateException("item code already in use");
        }
        itemRepository.save((item));

    }

    public void deleteItem(String code) {
        if (itemRepository.findByCode(code).isEmpty()) {

            throw new IllegalStateException("item does not exists");
        }
        System.out.println("HAW L CODE " + itemRepository.findByCode(code));
        Item item = itemRepository.findByCode(code).orElseThrow(EntityNotFoundException::new);
        itemRepository.delete(item);
    }

    public void updateItem(String code,Item updatedItem){
        Item item=itemRepository.findByCode(code)
                .orElseThrow(()->new EntityNotFoundException("Item not found with code: "+code));
        System.out.println(updatedItem.getName());
        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        item.setDescription(updatedItem.getDescription());
        item.setImage(updatedItem.getImage());
        item.setInventoryStatus(item.getInventoryStatus());
        itemRepository.save(item);
    }



}
