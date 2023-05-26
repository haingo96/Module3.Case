package service;

import model.House;

import java.time.LocalDate;
import java.util.List;


public interface CustomerInterface {

    List<House> findAll();

    //        List<House> findIndex(int address);
    List<House> findIndexID(int status1, LocalDate unavailable_until);

}
