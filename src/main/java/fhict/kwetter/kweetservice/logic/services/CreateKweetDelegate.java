package fhict.kwetter.kweetservice.logic.services;

import fhict.kwetter.kweetservice.persistence.model.Kweet;

public interface CreateKweetDelegate
{
    Kweet createKweet(Kweet kweet);
}
