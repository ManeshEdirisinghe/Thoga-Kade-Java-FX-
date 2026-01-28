package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String id;
    private String title;
    private String name;
    private String dob;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}