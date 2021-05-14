package fhict.kwetter.kweetservice.logic.services;

import fhict.kwetter.kweetservice.persistence.model.Kweet;
import fhict.kwetter.kweetservice.persistence.repository.KweetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetKweetService implements GetKweetsDelegate
{
    @Autowired
    private final KweetRepository kweetRepository;

    @Override
    public List<Kweet> retrieveKweets()
    {
        return kweetRepository.findAll();
    }
}
