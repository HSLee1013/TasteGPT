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
@JsonPropertyOrder({ "cafe_num", "cafe_tag" })
@Generated("jsonschema2pojo")
public class Cafe_Tag {

	@JsonProperty("cafe_num")
	private Integer cafeNum;
	@JsonProperty("cafe_tag")
	private String cafeTag;
}
