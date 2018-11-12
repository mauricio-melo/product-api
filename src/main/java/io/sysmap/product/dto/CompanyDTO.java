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
public class CompanyDTO {

    @ApiModelProperty(notes = "Id's company.", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Cnpj's product.", example = "81270418000110", required = true, position = 2)
    private Long cnpj;

    @ApiModelProperty(notes = "Status's company", example = "true", required = true, position = 4)
    private boolean enabled;

    @ApiModelProperty(notes = "User that create the company", example = "Mauricio Melo", required = true, position = 5)
    private String userCreation;
}
