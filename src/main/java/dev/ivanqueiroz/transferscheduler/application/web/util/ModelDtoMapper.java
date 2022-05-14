package dev.ivanqueiroz.transferscheduler.application.web.util;

import dev.ivanqueiroz.transferscheduler.application.web.dto.TransferDto;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModelDtoMapper {
  private final ModelMapper modelMapper;

  public TransferDto convertToDto(Transfer model) {
    return modelMapper.map(model, TransferDto.class);
  }
}
