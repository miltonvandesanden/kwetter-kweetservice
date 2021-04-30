package fhict.kwetter.kweetservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DtoWrapper<T extends Dto>
{
    private T data;
}
