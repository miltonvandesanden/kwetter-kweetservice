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
    public Kweet createKweet(Kweet kweet) throws NullPointerException
    {
        if(kweet == null) {
            throw new NullPointerException();
        }

        Kweet result = kweetRepository.save(kweet);

        try {
            sender.send(result.getHashtags());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        return result;
    }
}
