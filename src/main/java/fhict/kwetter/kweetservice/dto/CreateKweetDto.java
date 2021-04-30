package fhict.kwetter.kweetservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateKweetDto implements Validatable
{
    private String userId;
    private String message;

    @Override
    public boolean isValid()
    {
        return validateUserId() && validateMessage();
    }

    private boolean validateUserId()
    {
        try
        {
            return userId != null && !userId.isBlank() && Long.valueOf(userId) >= 0;
        } catch (NumberFormatException numberFormatException)
        {
            return false;
        }
    }

    private boolean validateMessage()
    {
        return message != null && !message.isBlank();
    }
}
