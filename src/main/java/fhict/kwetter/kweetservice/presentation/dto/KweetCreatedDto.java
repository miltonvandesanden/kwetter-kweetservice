package fhict.kwetter.kweetservice.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KweetCreatedDto implements Dto
{
    private String userId;
    private String message;
    private List<String> hashtags;
}
