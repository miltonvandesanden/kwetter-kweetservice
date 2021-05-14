package fhict.kwetter.kweetservice.logic.services;

import fhict.kwetter.kweetservice.persistence.model.Kweet;

import java.util.List;

public interface GetKweetsDelegate
{
    List<Kweet> retrieveKweets();
}
