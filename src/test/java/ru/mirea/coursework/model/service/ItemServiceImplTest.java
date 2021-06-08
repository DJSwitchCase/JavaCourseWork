package ru.mirea.coursework.model.service;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mirea.coursework.model.entity.Item;
import ru.mirea.coursework.model.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ItemServiceImplTest {
    @InjectMocks
    private ItemServiceImpl itemService;
    @Mock
    private ItemRepository itemsRepository;


    @Test
    public void findById() {
        Item item1 = new Item();
        item1.setId(145);

        Item item2 = new Item();
        item2.setId(157);
        

        Mockito.when(itemsRepository.findById(145)).thenReturn(item1);
        Mockito.when(itemsRepository.findById(157)).thenReturn(item2);

        Item item1orNot = itemService.findById(145);
        Item item2orNot = itemService.findById(157);

        Assertions.assertEquals(item1.getId(), item1orNot.getId());
        Assertions.assertEquals(item2.getId(), item2orNot.getId());
    }
}