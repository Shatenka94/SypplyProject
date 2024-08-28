package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.swing.plaf.synth.Region;
import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {

    private String logo;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private String id;
    private String blocked;
    private String status;
    private String createdAt;
    List<CustomResponse> company;
    private int regionId;
    private int companyId;





}
