package fhict.kwetter.kweetservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HandshakeDto implements Dto
{
    private final String MESSAGE = "Service up and running!";
}
