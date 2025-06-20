package com.example.demo.dto.external;

public record ExternalCharacterDTO(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        NestedLocation origin,
        NestedLocation location,
        String image
) {
    public record NestedLocation(String name, String url) {}
}