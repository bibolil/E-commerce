package com.example.ecommerce.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping(path = "item")
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService=itemService;
    }

   // @PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    @GetMapping(path="getItems")
    public List<Item> getItems() {
        System.out.println("this is from getting items ");
        return this.itemService.getItems();}

    @GetMapping(path="getItem/{itemCode}")
    public Optional<Item> getItem(@PathVariable("itemCode") String code)
    {
        return this.itemService.getItem(code);
    }

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
