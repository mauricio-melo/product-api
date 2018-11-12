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
public class FeatureDTO {

    @ApiModelProperty(notes = "Id's feature.", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Name's feature.", example = "Envio de SMS", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Description's feature.", example = "Funcionalidade de Envio de SMS", required = true, position = 3)
    private String description;

    @ApiModelProperty(notes = "Status's feature", example = "true", required = true, position = 4)
    private boolean enabled;

    @ApiModelProperty(notes = "User that create the feature", example = "Mauricio Melo", required = true, position = 5)
    private String userCreation;

    @ApiModelProperty(notes = "Id's bunch", example = "1", required = true, position = 6)
    private Long idBunch;
}
