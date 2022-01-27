package com.example.demo.data;

import com.example.demo.model.Category;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.Phone;
import com.example.demo.model.ShoppingCart;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Phone> phones = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

//    @PostConstruct
//    public void init(){
//        Category category1 = new Category("Mobile Phone");
//        Manufacturer manufacturer1 = new Manufacturer("Apple","USA");
//        Manufacturer manufacturer2 = new Manufacturer("Xiaomi","China");
//        Manufacturer manufacturer3 = new Manufacturer("Samsung","South Korea");
//        Manufacturer manufacturer4 = new Manufacturer("Huawei","China");
//        categories.add(new Category("Mobile Phone"));
//        categories.add(new Category("Accessory"));
//        manufacturers.add(new Manufacturer("Samsung","South Korea"));
//        manufacturers.add(new Manufacturer("Nokia","Finland"));
//        manufacturers.add(new Manufacturer("Huawei","China"));
//        manufacturers.add(new Manufacturer("Xiaomi","China"));
//        manufacturers.add(new Manufacturer("Apple","USA"));
//        phones.add(new Phone("iPhone13",500.0,manufacturer1,category1));
//        phones.add(new Phone("A52",300.0,manufacturer3,category1));
//        phones.add(new Phone("P40",250.0,manufacturer4,category1));
//        phones.add(new Phone("Redmi note",310.0,manufacturer2,category1));
//        phones.add(new Phone("A12",290.0,manufacturer3,category1));
//        phones.add(new Phone("iPhone12",450.0,manufacturer1,category1));
//        phones.add(new Phone("P50 Pro",310.0,manufacturer4,category1));
//
//    }

}
