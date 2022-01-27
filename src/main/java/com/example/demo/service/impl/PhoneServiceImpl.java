package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.Phone;
import com.example.demo.model.dto.PhoneDto;
import com.example.demo.model.exceptions.CategoryNotFoundException;
import com.example.demo.model.exceptions.ManufacturerNotFoundException;
import com.example.demo.model.exceptions.PhoneNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ManufacturerRepository;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.service.PhoneService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.phoneRepository = phoneRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Phone> findAll() {
        return this.phoneRepository.findAll();
    }

    @Override
    public Optional<Phone> findById(Long id) {
        return this.phoneRepository.findById(id);
    }

    @Override
    public List<Phone> findByNameLike(String name) {
        return this.phoneRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public Optional<Phone> save(String name, Double price, String image,Integer weight, Double size, String resolution, String OS, String cpu, String memory, String camera, String battery, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()-> new ManufacturerNotFoundException(manufacturerId));
        this.phoneRepository.deleteByName(name);

        return Optional.of(this.phoneRepository.save(new Phone(name,price,image,weight,size,resolution,OS,cpu,memory,camera,battery,manufacturer,category)));
    }

    @Override
    public Optional<Phone> save(PhoneDto phoneDto) {
        Category category = this.categoryRepository.findById(phoneDto.getCategory())
                .orElseThrow(()-> new CategoryNotFoundException(phoneDto.getCategory()));
        Manufacturer manufacturer = this.manufacturerRepository.findById(phoneDto.getManufacturer())
                .orElseThrow(()-> new ManufacturerNotFoundException(phoneDto.getManufacturer()));

        this.phoneRepository.deleteByName(phoneDto.getName());
        return Optional.of(this.phoneRepository.save(new Phone(phoneDto.getName(), phoneDto.getPrice(),phoneDto.getImage(),phoneDto.getWeight(),
                phoneDto.getSize(),phoneDto.getResolution(),phoneDto.getOS(),phoneDto.getCpu(),
                phoneDto.getMemory(),phoneDto.getCamera(),phoneDto.getBattery(),manufacturer,category)));
    }

    @Override
    @Transactional
    public Optional<Phone> edit(Long id, String name, Double price, String image,Integer weight, Double size, String resolution, String OS, String cpu, String memory, String camera, String battery, Long categoryId, Long manufacturerId) {
        Phone phone = this.phoneRepository.findById(id).orElseThrow(()-> new PhoneNotFoundException(id));
        phone.setName(name);
        phone.setPrice(price);
        phone.setImage(image);
        phone.setWeight(weight);
        phone.setSize(size);
        phone.setResolution(resolution);
        phone.setMemory(memory);
        phone.setBattery(battery);
        phone.setCamera(camera);
        phone.setCpu(cpu);
        phone.setOS(OS);
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException(categoryId));
        phone.setCategory(category);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));
        phone.setManufacturer(manufacturer);
        return Optional.of(this.phoneRepository.save(phone));
    }

    @Override
    public Optional<Phone> edit(Long id, PhoneDto phoneDto) {
        Phone phone = this.phoneRepository.findById(id).orElseThrow(()-> new PhoneNotFoundException(id));
        phone.setName(phoneDto.getName());
        phone.setPrice(phoneDto.getPrice());
        phone.setImage(phoneDto.getImage());
        phone.setWeight(phoneDto.getWeight());
        phone.setSize(phoneDto.getSize());
        phone.setResolution(phoneDto.getResolution());
        phone.setMemory(phoneDto.getMemory());
        phone.setBattery(phoneDto.getBattery());
        phone.setCamera(phoneDto.getCamera());
        phone.setCpu(phoneDto.getCpu());
        phone.setOS(phoneDto.getOS());

        Category category = this.categoryRepository.findById(phoneDto.getCategory())
                .orElseThrow(()-> new CategoryNotFoundException(phoneDto.getCategory()));
        phone.setCategory(category);
        Manufacturer manufacturer = this.manufacturerRepository.findById(phoneDto.getManufacturer())
                .orElseThrow(()-> new ManufacturerNotFoundException(phoneDto.getManufacturer()));
        phone.setManufacturer(manufacturer);

        return Optional.of(this.phoneRepository.save(phone));
    }

    @Override
    public void deleteById(Long id) {
        this.phoneRepository.deleteById(id);
    }

}
