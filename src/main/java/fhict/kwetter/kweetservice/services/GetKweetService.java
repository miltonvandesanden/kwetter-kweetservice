package fhict.kwetter.kweetservice.services;

import fhict.kwetter.kweetservice.controller.KweetController;
import fhict.kwetter.kweetservice.dto.KweetCreatedDto;
import fhict.kwetter.kweetservice.model.Kweet;
import fhict.kwetter.kweetservice.repository.KweetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetKweetService implements KweetController.RetrieveKweetsDelegate
{
    @Autowired
    private final KweetRepository kweetRepository;

    @Override
    public List<KweetCreatedDto> retrieveKweets()
    {
        return kweetsToKweetDtos(kweetRepository.findAll());
    }

    public List<KweetCreatedDto> kweetsToKweetDtos(List<Kweet> kweets)
    {
        List<KweetCreatedDto> kweetCreatedDtos = new ArrayList<>();

        for (Kweet kweet: kweets)
        {
            kweetCreatedDtos.add(kweetToKweetDto(kweet));
        }

        return kweetCreatedDtos;
    }

    public KweetCreatedDto kweetToKweetDto(Kweet kweet)
    {
        return KweetCreatedDto.builder()
                .userId(String.valueOf(kweet.getUserId()))
                .message(kweet.getMessage())
                .hashtags(kweet.getHashtags())
                .build();
    }
}
