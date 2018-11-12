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
public class BunchDTO {

    @ApiModelProperty(notes = "Id's bunch.", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Name's bunch.", example = "Automação", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Description's bunch.", example = "Função automação", required = true, position = 3)
    private String description;

    @ApiModelProperty(notes = "Status's bunch", example = "true", required = true, position = 4)
    private boolean enabled;

    @ApiModelProperty(notes = "User that create the bunch", example = "Mauricio Melo", required = true, position = 5)
    private String userCreation;

    @ApiModelProperty(notes = "Id's product ", example = "1", required = true, position = 6)
    private Long idProduct;
}
