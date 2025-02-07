package ru.mirea.coursework.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mirea.coursework.model.entity.Item;
import ru.mirea.coursework.model.repository.ItemRepository;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ItemServiceImplTest {
    @InjectMocks
    private ItemServiceImpl itemService;
    @Mock
    private ItemRepository itemsRepository;
    @Mock
    private EmailService emailService;
    @Captor
    private ArgumentCaptor<Item> captorItem;
    @Captor
    private ArgumentCaptor<Integer> captorInteger;
    @Test
    public void shouldRead() {
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
    @Test
    public void shouldCreate(){
        Item item1 = new Item();
        item1.setId(1);
        itemService.create(item1);
        Mockito.verify(itemsRepository).save(captorItem.capture());
        Item captured1 = captorItem.getValue();
        Assertions.assertEquals(1, captured1.getId());
    }

    @Test
    public void shouldReadAll(){
        Item item1 = new Item();
        Item item2 = new Item();
        Mockito.when(itemsRepository.findAll()).thenReturn(List.of(item1, item2));
        Assertions.assertEquals(itemService.readAll(), List.of(item1, item2));
    }
    @Test
    public void shouldFindByName(){
        Item item1 = new Item();
        item1.setName("Alexander");
        Item item2 = new Item();
        item2.setName("Alexey");

        Mockito.when(itemsRepository.findByName("Alexander")).thenReturn(item1);
        Mockito.when(itemsRepository.findByName("Alexey")).thenReturn(item2);

        Item item1orNot = itemService.findByName("Alexander");
        Item item2orNot = itemService.findByName("Alexey");

        Assertions.assertEquals(item1.getName(), item1orNot.getName());
        Assertions.assertEquals(item2.getName(), item2orNot.getName());
    }

    @Test
    public void shouldDelete(){
        Item item = new Item();

        item.setId(4);
        Mockito.when(itemsRepository.existsById(4)).thenReturn(true);

        itemService.delete(4);
        Mockito.verify(itemsRepository).deleteById(captorInteger.capture());
        int captured = captorInteger.getValue();
        Assertions.assertEquals(4, 4);
    }

    @Test
    public void shouldUpdate(){
        Item item = new Item();
        item.setId(8);

        Mockito.when(itemsRepository.existsById(12)).thenReturn(false);

        itemService.update(item, 12);
        Mockito.verify(itemsRepository).save(captorItem.capture());

        Item captured = captorItem.getValue();

        Assertions.assertEquals(12, captured.getId());
    }
}