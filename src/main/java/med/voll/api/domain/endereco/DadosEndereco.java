package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank

        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String UF,
        String numero,
        String complemento) {
}
