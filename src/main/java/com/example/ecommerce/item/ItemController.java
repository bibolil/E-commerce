package com.example.ecommerce.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "item")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService=itemService;
    }

    @PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    @GetMapping(path="getItems")
    public List<Item> getItems() {return this.itemService.getItems();}

    @PreAuthorize("hasAnyAuthority('admin:create','user:create')")
    @PostMapping(path="addItem")
    public void addItem(@RequestBody Item item)
    {
        this.itemService.addNewItem(item);
    }

    @PreAuthorize("hasAnyAuthority('admin:delete','user:delete')")
    @DeleteMapping(path="deleteItem/{itemCode}")
    public void deleteItem(@PathVariable("itemCode") long code)
    {
        this.itemService.deleteItem(code);
    }

    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    @PutMapping(path="updateItem/{itemId}")
    public void updateItem(@PathVariable("itemId") long code,
    @RequestBody Item updatedItem)
    {
        this.itemService.updateItem(code,updatedItem);
    }




}
