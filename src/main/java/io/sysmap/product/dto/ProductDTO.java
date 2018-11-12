package io.sysmap.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @ApiModelProperty(notes = "Id's product.", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Name's product.", example = "Basic", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Description's product.", example = "Produto Basic", required = true, position = 3)
    private String description;

    @ApiModelProperty(notes = "Status's product", example = "true", required = true, position = 4)
    private boolean enabled;

    @ApiModelProperty(notes = "User that create the product", example = "Mauricio Melo", required = true, position = 5)
    private String userCreation;
}
