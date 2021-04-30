package fhict.kwetter.kweetservice.dto;

import fhict.kwetter.kweetservice.model.Kweet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class KweetCreatedDto implements Dto
{
    private String userId;
    private String message;

    public KweetCreatedDto(Kweet kweet)
    {
        this.userId = String.valueOf(kweet.getUserId());
        this.message = kweet.getMessage();
    }
}
