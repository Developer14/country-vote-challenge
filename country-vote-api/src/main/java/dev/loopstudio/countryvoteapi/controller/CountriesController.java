package dev.loopstudio.countryvoteapi.controller;

import dev.loopstudio.countryvoteapi.dto.CountryDto;
import dev.loopstudio.countryvoteapi.dto.VotedCountryDto;
import dev.loopstudio.countryvoteapi.service.VotedCountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Countries controller.
 *
 * @author Victor Morales
 */
@RestController
@RequestMapping("/api/v1/countries")
public class CountriesController {

    private final VotedCountryService votedCountryService;

    @Autowired
    public CountriesController(VotedCountryService votedCountryService) {
        this.votedCountryService = votedCountryService;
    }

    /**
     * Gets most voted operation.
     *
     * @return the ten most voted countries list
     */
    @Operation(
            description = "Retrieve list of ten most voted countries",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Most voted countries retrieved successfully",
                            content = @Content(schema = @Schema(implementation = VotedCountryDto.class))),
                    @ApiResponse(responseCode = "204", description = "No voted countries yet",
                            content = @Content(schema = @Schema()))
            }
    )
    @GetMapping("/most-voted")
    public ResponseEntity<List<VotedCountryDto>> getMostVoted() {

        List<VotedCountryDto> mostVoted = votedCountryService.getMostVoted();
        if (mostVoted.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mostVoted);
    }

    /**
     * Gets country list operation.
     *
     * @return the country name list
     */
    @Operation(
            description = "Retrieve list of country names",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Country names list retrieved successfully",
                            content = @Content(schema = @Schema(implementation = VotedCountryDto.class))),
                    @ApiResponse(responseCode = "204", description = "No countries",
                            content = @Content(schema = @Schema()))
            }
    )
    @GetMapping("")
    public ResponseEntity<List<CountryDto>> getCountryNameList() {
        List<CountryDto> simpleCountryList = votedCountryService.getSimpleCountryList();
        if (simpleCountryList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(simpleCountryList);
    }

}
