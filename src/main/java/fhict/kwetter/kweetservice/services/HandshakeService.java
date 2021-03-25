package fhict.kwetter.kweetservice.services;

import fhict.kwetter.kweetservice.controller.KweetController;
import fhict.kwetter.kweetservice.dto.HandshakeDTO;
import org.springframework.stereotype.Service;

@Service
public class HandshakeService implements KweetController.HandshakeDelegate
{
    @Override
    public HandshakeDTO doHandshake()
    {
        return HandshakeDTO.builder().build();
    }
}
