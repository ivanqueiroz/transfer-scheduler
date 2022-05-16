package dev.ivanqueiroz.transferscheduler.application.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ivanqueiroz.transferscheduler.application.web.dto.TransferDto;
import dev.ivanqueiroz.transferscheduler.domain.entities.Transfer;
import dev.ivanqueiroz.transferscheduler.domain.exceptions.AccountNotFoundException;
import dev.ivanqueiroz.transferscheduler.domain.services.TransferMockFactory;
import dev.ivanqueiroz.transferscheduler.domain.services.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransferControllerTest {

    @MockBean
    private TransferService transferService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("when send a get request, list all schedules succesfuly")
    void getAllSchedulesSuccess(@Autowired MockMvc mvc) throws Exception {
        var pageRequest = PageRequest.of(0, 20);
        var list = List.of(TransferMockFactory.createMockTransfer());
        given(transferService.listSchedules(pageRequest)).willReturn(new PageImpl<>(list, pageRequest, list.size()));

        mvc.perform(get("/v1/schedule")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content[*].id").isNotEmpty());

        verify(transferService, times(1)).listSchedules(pageRequest);
    }

    @Test
    @DisplayName("when send a get request, no records in database, should return 4040")
    void getEmptyResult(@Autowired MockMvc mvc) throws Exception {

        var pageRequest = PageRequest.of(0, 20);
        given(transferService.listSchedules(pageRequest)).willReturn(new PageImpl<>(Collections.emptyList()));

        mvc.perform(get("/v1/schedule"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"));

        verify(transferService, times(1)).listSchedules(pageRequest);
    }

    @Test
    @DisplayName("when send a post transfer request, but no account found in database, should return 404")
    void postWithNoAccount(@Autowired MockMvc mvc) throws Exception {
        var request = TransferMockFactory.createMockTransferDto();
        given(transferService.schedule(any())).willThrow(AccountNotFoundException.class);
        mvc.perform(post("/v1/schedule").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().
                isNotFound()).andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("when send a post transfer request, without a required field, show return 422")
    void postTransferWithoutRequiredField(@Autowired MockMvc mvc) throws Exception {
        var request = TransferMockFactory.createMockInvalidTransferDto();
        mvc.perform(post("/v1/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("when send a post transfer request, had a internal error, show return 500")
    void postTransferInternalException(@Autowired MockMvc mvc) throws Exception {
        var request = TransferMockFactory.createMockTransferDto();
        given(transferService.schedule(any())).willThrow(RuntimeException.class);
        mvc.perform(post("/v1/schedule")
                        .contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void postSucess(@Autowired MockMvc mvc) throws Exception {

        Transfer transfer = TransferMockFactory.createMockTransfer();
        given(transferService.schedule(any())).willReturn(transfer);
        var request = TransferDto.valueOf(transfer);
        mvc.perform(post("/v1/schedule").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.amount").value(BigDecimal.TEN))
                .andExpect(jsonPath("$.taxAmount").value(BigDecimal.valueOf(12)));

        verify(transferService, times(1)).schedule(any());
    }

}
