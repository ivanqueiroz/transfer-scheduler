package dev.ivanqueiroz.transferscheduler.application.web.controller;

import dev.ivanqueiroz.transferscheduler.application.web.dto.TransferDto;
import dev.ivanqueiroz.transferscheduler.application.web.util.ModelDtoMapper;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.TransferNotFoundException;
import dev.ivanqueiroz.transferscheduler.domain.services.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/schedule")
public class TransferController {

  private final TransferService transferService;
  private final ModelDtoMapper modelDtoMapper;

  @GetMapping
  Page<TransferDto> getAllSchedules(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "20") int size,
    @RequestParam(value = "lang", defaultValue = "US") String lang) {
    var pageable = PageRequest.of(page, size);
    log.info("Searching schedules...");
    List<TransferDto> listSchedules = transferService.listSchedules(pageable).stream().toList().stream().map(modelDtoMapper::convertToDto).toList();
    log.info("Return schedules %s".formatted(listSchedules.size()));
    if (listSchedules.isEmpty()) {
      throw new TransferNotFoundException("No transfers found");
    }
    return new PageImpl<>(listSchedules, pageable, listSchedules.size());
  }

}
