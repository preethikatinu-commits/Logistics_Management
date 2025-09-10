package com.example.demo.dto;

import com.example.demo.entity.Branch;
import com.example.demo.entity.BranchType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BranchDto {
	
	
	  private Long id; 
	@NotBlank(message = "Branch name is required")
    @Size(max = 255, message = "Branch name cannot exceed 255 characters")
    private String name;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @Size(max = 100, message = "State cannot exceed 100 characters")
    private String state;

    @Size(max = 20, message = "Pin code cannot exceed 20 characters")
    private String pinCode;

    // store BranchType as String in DTO (IMPORT/EXPORT/LOCAL)
    private String type;

    // selected client id from form
    private Long clientId;

    // optional helper for views
    private String clientName;

    public BranchDto() {}

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    // --- Mappers ---
    public static BranchDto fromEntity(Branch branch) {
        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setAddress(branch.getAddress());
        dto.setCity(branch.getCity());
        dto.setState(branch.getState());
        dto.setPinCode(branch.getPinCode());
        dto.setType(branch.getType() != null ? branch.getType().name() : null);
        dto.setClientId(branch.getClient() != null ? branch.getClient().getId() : null);
        dto.setClientName(branch.getClient() != null ? branch.getClient().getName() : null);
        return dto;
    }

    public static Branch toEntity(BranchDto dto) {
        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setName(dto.getName());
        branch.setAddress(dto.getAddress());
        branch.setCity(dto.getCity());
        branch.setState(dto.getState());
        branch.setPinCode(dto.getPinCode());
        if (dto.getType() != null && !dto.getType().isBlank()) {
            branch.setType(Enum.valueOf(BranchType.class, dto.getType().toUpperCase()));
        }
        return branch;
    }
}
  
