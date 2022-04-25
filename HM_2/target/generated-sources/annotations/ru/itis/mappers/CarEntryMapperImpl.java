package ru.itis.mappers;

import org.springframework.stereotype.Component;
import ru.itis.dto.CarEntryDto;
import ru.itis.dto.EntryForm;
import ru.itis.models.CarEntry;
import ru.itis.models.Parking;
import ru.itis.models.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-25T22:41:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
*/
@Component
public class CarEntryMapperImpl implements CarEntryMapper {

    @Override
    public CarEntryDto toResponse(CarEntry carEntry) {
        if ( carEntry == null ) {
            return null;
        }

        CarEntryDto carEntryDto = new CarEntryDto();

        carEntryDto.setUserId( carEntryUserId( carEntry ) );
        carEntryDto.setParkingId( carEntryParkingId( carEntry ) );
        carEntryDto.setModel( carEntry.getModel() );
        carEntryDto.setCarNumber( carEntry.getCarNumber() );
        carEntryDto.setColor( carEntry.getColor() );
        carEntryDto.setStartTime( carEntry.getStartTime() );
        carEntryDto.setEndTime( carEntry.getEndTime() );

        return carEntryDto;
    }

    @Override
    public CarEntry toRequest(EntryForm entryForm) {
        if ( entryForm == null ) {
            return null;
        }

        CarEntry carEntry = new CarEntry();

        carEntry.setUser( entryFormToUser( entryForm ) );
        carEntry.setModel( entryForm.getModel() );
        carEntry.setCarNumber( entryForm.getCarNumber() );
        carEntry.setColor( entryForm.getColor() );

        return carEntry;
    }

    private Long carEntryUserId(CarEntry carEntry) {
        if ( carEntry == null ) {
            return null;
        }
        User user = carEntry.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long carEntryParkingId(CarEntry carEntry) {
        if ( carEntry == null ) {
            return null;
        }
        Parking parking = carEntry.getParking();
        if ( parking == null ) {
            return null;
        }
        Long id = parking.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User entryFormToUser(EntryForm entryForm) {
        if ( entryForm == null ) {
            return null;
        }

        User user = new User();

        user.setId( entryForm.getUserId() );

        return user;
    }
}
