package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.CarEntryRepository;
import ru.itis.dto.EntryForm;
import ru.itis.exceptions.ErrorEntity;
import ru.itis.exceptions.NotFoundException;
import ru.itis.models.CarEntry;
import ru.itis.services.CarEntryService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarEntryServiceImpl implements CarEntryService {

    private final CarEntryRepository carEntryRepository;

    @Override
    public String canCarGoOut(EntryForm entryForm) {
        Optional<CarEntry> carEntry = carEntryRepository.findByCarNumber(entryForm.getCarNumber());
        if(carEntry.isEmpty()) {
            throw new NotFoundException(ErrorEntity.CAR_ARE_NOT_IN_THIS_PARKING);
        } else if(carEntry.get().getEndTime().compareTo(entryForm.getEndTime()) <= 0) {
            return "YES";
        }
        return "NO";
    }

}
