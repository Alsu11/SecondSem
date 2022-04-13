package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.CarEntryRepository;
import ru.itis.dao.ParkingRepository;
import ru.itis.dao.UserRepository;
import ru.itis.dto.CarEntryDto;
import ru.itis.dto.EntryForm;
import ru.itis.exceptions.*;
import ru.itis.models.CarEntry;
import ru.itis.models.Parking;
import ru.itis.models.User;
import ru.itis.services.ParkingService;

import java.time.Instant;
import java.util.Optional;

import static ru.itis.dto.CarEntryDto.from;

@RequiredArgsConstructor
@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    private final CarEntryRepository carEntryRepository;

    private final UserRepository userRepository;

    @Override
    public Integer getSlotsCount(String address) {
        Optional<Parking> parkingOptional = parkingRepository.findByAddress(address);

        if(parkingOptional.isPresent()) {
            return parkingOptional.get().getSlotsCount();
        } else {
            throw new IncorrectInput(ErrorEntity.ADDRESS_WRONG);
        }
    }

    @Override
    public CarEntryDto parkCar(EntryForm entryForm) {
        carEntryRepository.findByCarNumber(entryForm.getCarNumber()).orElseThrow(() -> new AlreadyExistsException(ErrorEntity.CAR_ALREADY_HERE));
        Integer slotsCount = getSlotsCount(entryForm.getAddress());

        if(slotsCount == 0) {
            throw new NoPlaceException(ErrorEntity.NO_PLACE);
        } else {
            User user = userRepository.findById(entryForm.getUserId()).orElseThrow(() -> new NotFoundException(ErrorEntity.USER_NOT_FOUND));
            Parking parking = parkingRepository.findByAddress(entryForm.getAddress()).orElseThrow(() -> new IncorrectInput(ErrorEntity.ADDRESS_WRONG) );

            CarEntry carEntry = CarEntry.builder()
                    .carNumber(entryForm.getCarNumber())
                    .color(entryForm.getColor())
                    .model(entryForm.getModel())
                    .parking(parking)
                    .user(user)
                    .startTime(entryForm.getStartTime())
                    .endTime(entryForm.getEndTime())
                    .build();

            Integer slots = parking.getSlotsCount() - 1;
            parking.setSlotsCount(slots);
            parking.setUpdatedAt(Instant.now());

            parkingRepository.save(parking);
            carEntry.setCreatedAt(Instant.now());

            return from(carEntryRepository.save(carEntry));
        }
    }

}
