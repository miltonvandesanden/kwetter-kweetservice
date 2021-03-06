package fhict.kwetter.kweetservice.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DtoList implements Dto
{
    private List<KweetCreatedDto> data;
}
