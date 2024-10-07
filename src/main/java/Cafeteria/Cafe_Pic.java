package Cafeteria;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "picNumber", "cafePic", "cafeNum" })
@Generated("jsonschema2pojo")
public class Cafe_Pic {

	@JsonProperty("pic_number")
	private Integer picNumber;
	@JsonProperty("cafe_pic")
	private String cafePic;
	@JsonProperty("cafe_num")
	private Integer cafeNum;

}