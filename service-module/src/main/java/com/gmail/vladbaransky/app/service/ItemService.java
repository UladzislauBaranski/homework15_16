package com.gmail.vladbaransky.app.service;

import com.gmail.vladbaransky.app.service.model.ItemDTO;

public interface ItemService {
    void add(ItemDTO itemDTO);

    void delete();
}
