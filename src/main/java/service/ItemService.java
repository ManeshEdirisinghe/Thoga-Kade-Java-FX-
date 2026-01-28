package service;

import model.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    boolean addItem(ItemDTO itemDTO);

    boolean updateItem(ItemDTO itemDTO);

    boolean deleteItem(String itemCode);

    ItemDTO getItemById(String itemCode);

    List<ItemDTO> getAllItems();
}
