package com.example.demo.service.family;

import com.example.demo.repository.family.ChildRepository;
import com.example.demo.repository.family.ParentRepository;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl implements FamilyService{
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    public FamilyServiceImpl(ParentRepository parentRepository, ChildRepository childRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }
}
