package fhict.kwetter.kweetservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HandshakeDTO implements DTO
{
    private final String MESSAGE = "Service up and running!";
}
