package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FootballerRepository extends CrudRepository<Footballer, Integer>{

    List<Footballer> findAllByUser(User user);
}
