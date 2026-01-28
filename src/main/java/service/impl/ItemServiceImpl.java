package service.impl;

import model.dto.ItemDTO;
import model.entity.Item;
import repository.custom.ItemRepository;
import repository.custom.impl.ItemRepositoryImpl;
import service.ItemService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    public boolean addItem(ItemDTO itemDTO) {
        try {
            Item item = convertToEntity(itemDTO);
            return itemRepository.save(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        try {
            Item item = convertToEntity(itemDTO);
            return itemRepository.update(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String itemCode) {
        try {
            return itemRepository.delete(itemCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemDTO getItemById(String itemCode) {
        try {
            Item item = itemRepository.findById(itemCode);
            if (item != null) {
                return convertToDTO(item);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDTO> itemDTOs = new ArrayList<>();

            for (Item item : items) {
                itemDTOs.add(convertToDTO(item));
            }
            return itemDTOs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to convert DTO to Entity
    private Item convertToEntity(ItemDTO dto) {
        return new Item(
                dto.getItemCode(),
                dto.getDescription(),
                dto.getPackSize(),
                dto.getUnitPrice(),
                dto.getQtyOnHand()
        );
    }

    // Helper method to convert Entity to DTO
    private ItemDTO convertToDTO(Item entity) {
        return new ItemDTO(
                entity.getItemCode(),
                entity.getDescription(),
                entity.getPackSize(),
                entity.getUnitPrice(),
                entity.getQtyOnHand()
        );
    }
}
