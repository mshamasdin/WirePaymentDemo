package WiresDemo.WiresDemo.model.request.Pacs008;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostalAddress {
    Type address_type;
    private String department;
    private String sub_department;
    private String street_name;
    private String building_number;
    private String building_name;
    private String post_code;
    private String town_name;
    private String country_sub_division;
    private String country;
    ArrayList< String > address_line = new ArrayList <> ();
}
