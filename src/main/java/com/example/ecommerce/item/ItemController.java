package com.example.ecommerce.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService=itemService;
    }

    @GetMapping(path="getItems")
    public List<Item> getItems() {return this.itemService.getItems();}

    @PostMapping(path="addItem")
    public void addItem(@RequestBody Item item)
    {
        this.itemService.addNewItem(item);
    }

    @DeleteMapping(path="deleteItem/{itemCode}")
    public void deleteItem(@PathVariable("itemCode") long code)
    {
        this.itemService.deleteItem(code);
    }

    @PutMapping(path="updateItem/{itemId}")
    public void updateItem(@PathVariable("itemId") long code,
    @RequestBody Item updatedItem)
    {
        this.itemService.updateItem(code,updatedItem);
    }




}
