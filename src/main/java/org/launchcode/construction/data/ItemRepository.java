package org.launchcode.construction.data;

import org.launchcode.construction.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LaunchCode
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByPrice(Double price);
    List<Item> findByNewItem(boolean newItem);
    List<Item> findByPriceAndNewItem(Double price, boolean newItem);
}
