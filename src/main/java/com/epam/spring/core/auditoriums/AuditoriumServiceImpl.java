package com.epam.spring.core.auditoriums;

import com.epam.spring.core.shared.Mapper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

    private MapperFacade mapper = Mapper.getMapper();

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Override
    public List<Auditorium> getAuditoriums() {
        Iterable<AuditoriumEntity> all = auditoriumRepository.findAll();
        return mapper.mapAsList(all, Auditorium.class);
    }

    @Override
    public Auditorium getAuditorium(final String name) {
        AuditoriumEntity byName = auditoriumRepository.findByName(name);
        return mapper.map(byName, Auditorium.class);
    }
}
