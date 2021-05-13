package fhict.kwetter.kweetservice.messaging.dto;

import fhict.kwetter.kweetservice.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Component
public class HashtagsDto implements Dto, Serializable
{
    private List<String> hashtags;
}
