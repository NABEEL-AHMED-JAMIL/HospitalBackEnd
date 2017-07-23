package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.DeletableModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Lycus 01 on 7/15/2017.
 */
@NoRepositoryBean
public interface DeletableModelRepository<T extends DeletableModel>
        extends PagingAndSortingRepository<T, Long> {

    @Override
    @Query( "select m from #{#entityName} m where m.deletedAt IS NULL Order By m.id" )
    public List<T> findAll();

}
