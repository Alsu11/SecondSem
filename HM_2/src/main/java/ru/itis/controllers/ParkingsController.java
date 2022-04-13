package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.CarEntryDto;
import ru.itis.dto.EntryForm;
import ru.itis.services.ParkingService;

@RequiredArgsConstructor
@RestController
public class ParkingsController {

    private final ParkingService parkingService;

    @GetMapping("/slots")
    public Integer getSlotsCar(@RequestParam("address") String address) {
        return parkingService.getSlotsCount(address);
    }

    @PostMapping(value = "/park")
    public CarEntryDto parkCar(@RequestBody EntryForm entryForm){
        return parkingService.parkCar(entryForm);
    }

}
