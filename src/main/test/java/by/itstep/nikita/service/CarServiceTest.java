package by.itstep.nikita.service;

import by.itstep.nikita.domain.Car;
import by.itstep.nikita.repository.CarRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    CarService carService;

    @MockBean
    CarRepo carRepo;

    //    @Autowired
    Car carOne;
    //    @Autowired
    Car carTwo;


    @Before
    public void setUp() {
        carOne = new Car();
        carOne.setMark("Ford");
        carOne.setModel("Fiesta");
        carOne.setColor("Red");
        carOne.setVin("123456789qwertyui");
        carOne.setDeleted(false);
        carTwo = new Car();
        carTwo.setMark("Mazda");
        carTwo.setModel("RX-7");
        carTwo.setColor("Black");
        carTwo.setVin("qwertyuiop1234567");
        carTwo.setDeleted(true);
    }

    @Test
    public void saveCarTestNewCarPositive() {
        Mockito.when(carRepo.findByVin(carOne.getVin())).thenReturn(null);
        boolean carIsSaved = carService.saveCar(carOne);
        Assert.assertTrue(carIsSaved);

    }

    @Test
    public void saveCarTestNewCarNegative() {
        Mockito.when(carRepo.findByVin(carOne.getVin())).thenReturn(carOne);
        boolean carIsSaved = carService.saveCar(carOne);
        Assert.assertFalse(carIsSaved);
    }

    @Test
    public void saveCarTestExistedCarWithChangedVinPositive() {
        carOne.setId(1L);
        carOne.setVin(carTwo.getVin());
        Mockito.when(carRepo.findByVin(carOne.getVin())).thenReturn(null);
        boolean carIsSaved = carService.saveCar(carOne);
        Assert.assertTrue(carIsSaved);
    }

    @Test
    public void saveCarTestExistedCarWithChangedVinNegative() {
        carOne.setId(1L);
        carOne.setVin(carTwo.getVin());
        Mockito.when(carRepo.findByVin(carOne.getVin())).thenReturn(carTwo);
        boolean carIsSaved = carService.saveCar(carOne);
        Assert.assertFalse(carIsSaved);
    }

    @Test
    public void saveCarTestExistedCarWithNotChangedVinPositive() {
        carOne.setId(1L);
        Mockito.when(carRepo.findByVin(carOne.getVin())).thenReturn(carOne);
        boolean carIsSaved = carService.saveCar(carOne);
        Assert.assertTrue(carIsSaved);
    }

    @Test
    public void saveCarTestExistedCarWithNotChangedVinNegative() {
        carOne.setId(1L);
        carTwo.setId(2L);
        Mockito.when(carRepo.findByVin(carOne.getVin())).thenReturn(carTwo);
        boolean carIsSaved = carService.saveCar(carOne);
        Assert.assertFalse(carIsSaved);
    }

    @Test
    public void getAll() {

    }

    @Test
    public void remove() {

    }

    @Test
    public void repareCar() {

    }


}
