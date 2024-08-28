package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBody {
    private String name;
    private String time;
    private String coordinates;

    private int price;
    private boolean map;

    private int regionId;
    private int branchId;
    private String additionalInformation;
    private String type;
    private String email;

    private String password;
    private String company_name;
    private String seller_name;
    private String address;
    private String number;

}
