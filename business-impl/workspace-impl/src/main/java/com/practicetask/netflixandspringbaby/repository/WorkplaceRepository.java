package com.practicetask.netflixandspringbaby.repository;


import com.practicetask.netflixandspringbaby.domain.Workspace;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkplaceRepository extends CrudRepository<Workspace, Integer> {

    List<Workspace> findByUnit(Integer unit);
}
