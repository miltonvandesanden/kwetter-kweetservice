package fhict.kwetter.kweetservice.logic.services;

import fhict.kwetter.kweetservice.messaging.Sender;
import fhict.kwetter.kweetservice.persistence.model.Kweet;
import fhict.kwetter.kweetservice.persistence.repository.KweetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateKweetService implements CreateKweetDelegate
{
    @Autowired
    private final KweetRepository kweetRepository;

    @Autowired
    private final Sender sender;

    @Override
    public Kweet createKweet(Kweet kweet)
    {
        Kweet result = kweetRepository.save(kweet);

        sender.send(result.getHashtags());

        return result;
    }
}
