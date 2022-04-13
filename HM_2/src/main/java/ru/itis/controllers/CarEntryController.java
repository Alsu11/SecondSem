package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.EntryForm;
import ru.itis.services.CarEntryService;

@RequiredArgsConstructor
@RestController
public class CarEntryController {

    private final CarEntryService carEntryService;

    @PostMapping(value = "/can-go")
    public String canGo(@RequestBody EntryForm entryForm) {
        return carEntryService.canCarGoOut(entryForm);
    }
}
