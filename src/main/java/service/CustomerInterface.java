package service;

import model.House;

import java.util.List;


public interface CustomerInterface {

    List<House> findAll();
    int findIndex(String email);



}
