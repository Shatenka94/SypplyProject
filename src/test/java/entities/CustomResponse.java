package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CustomResponse {
        private int category_id;
        private String category_title;
        List<entities.CustomResponse> responses;
        private String email;
        private int seller_id;
private int id;

    }