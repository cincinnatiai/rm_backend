package com.example.demo.mapper;

import com.example.demo.dto.CharacterDTO;
import com.example.demo.dto.LocationDTO;
import com.example.demo.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationDTO locationToLocationDTO(Location location){

        return new LocationDTO(
                location.getLocationId(),
                location.getName(),
                location.getUrl()
        );
    }

    public Location locationDtoToLocation(LocationDTO locationDTO){
        return new Location(
                locationDTO.getLocationId(),
                locationDTO.getName(),
                locationDTO.getUrl()
        );
    }
}
