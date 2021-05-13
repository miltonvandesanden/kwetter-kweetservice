package fhict.kwetter.kweetservice.messaging;

import java.util.List;

public interface Sender
{
    void send(List<String> hashtags);
}
