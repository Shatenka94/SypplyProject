package entities;

import lombok.Data;

@Data
public class RequestBody {
    private String name;
    private String time;
    private String coordinates;
//"name": "string",
//        "time": "string",
//
//
//        "coordinates": "string",
//        "additionalInformation": "string",
//        "type": "CITY",
//        "regionId": 1,
//        "branchId": 1

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
