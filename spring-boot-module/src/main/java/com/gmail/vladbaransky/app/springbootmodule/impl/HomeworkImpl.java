package com.gmail.vladbaransky.app.springbootmodule.impl;

import com.gmail.vladbaransky.app.service.ItemService;
import com.gmail.vladbaransky.app.service.ItemShopService;
import com.gmail.vladbaransky.app.service.ShopService;
import com.gmail.vladbaransky.app.service.model.ItemDTO;
import com.gmail.vladbaransky.app.service.model.ItemShopDTO;
import com.gmail.vladbaransky.app.service.model.ShopDTO;
import com.gmail.vladbaransky.app.springbootmodule.Homework;
import com.gmail.vladbaransky.app.springbootmodule.util.RandomUtil;
import org.springframework.stereotype.Component;

@Component
public class HomeworkImpl implements Homework {
    private static final int NUMBER_OF_OBJECT = 10;
    private static final int NUMBER_OF_RELATION = 5;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10;
    private final ItemService itemService;
    private final ShopService shopService;
    private final ItemShopService itemShopService;

    public HomeworkImpl(ItemService itemService, ShopService shopService, ItemShopService itemShopService) {
        this.itemService = itemService;
        this.shopService = shopService;
        this.itemShopService = itemShopService;
    }

    @Override
    public void getHomework() {
        for (int i = 0; i < NUMBER_OF_OBJECT; i++) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName("Test" + i);
            itemDTO.setDescription("Description" + i);
            itemService.add(itemDTO);
        }
        for (int i = 0; i < NUMBER_OF_OBJECT; i++) {
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.setName("Shop" + i);
            shopDTO.setLocation("Location" + i);
            shopService.add(shopDTO);
        }
        for (int i = 1; i <= NUMBER_OF_RELATION; i++) {
            ItemShopDTO itemShopDTO = new ItemShopDTO();
            itemShopDTO.setIdItem(RandomUtil.getNumberInRange(MIN_VALUE, MAX_VALUE));
            itemShopDTO.setIdShop(RandomUtil.getNumberInRange(MIN_VALUE, MAX_VALUE));
            itemShopService.addRelations(itemShopDTO);
        }
        itemService.delete();
    }
}
