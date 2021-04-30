package fhict.kwetter.kweetservice.services;

import fhict.kwetter.kweetservice.controller.KweetController;
import fhict.kwetter.kweetservice.dto.HandshakeDto;
import org.springframework.stereotype.Service;

@Service
public class HandshakeService implements KweetController.HandshakeDelegate
{
    @Override
    public HandshakeDto doHandshake()
    {
        return HandshakeDto.builder().build();
    }
}
