package fhict.kwetter.kweetservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DTOWrapper<T extends DTO>
{
    private T data;
}
