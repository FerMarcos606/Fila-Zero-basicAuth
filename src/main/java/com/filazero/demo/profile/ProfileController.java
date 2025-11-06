package com.filazero.demo.profile;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filazero.demo.profile.dtos.ProfileRequestDTO;
import com.filazero.demo.profile.dtos.ProfileResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("{api-endpoint}/profiles")
@RequestMapping("${api-endpoint}/profile")
// @Tag(name = "Profile", description = "Endpoints for managing user profiles") swagger...
public class ProfileController {

    private final IProfileService profileService;

    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * update, and delete user profile information.
     */

     @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO dto) {
        ProfileResponseDTO created = profileService.createEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileRequestDTO dto) {
        return ResponseEntity.ok(profileService.updateEntity(id, dto));
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getEntities());
    }

}
