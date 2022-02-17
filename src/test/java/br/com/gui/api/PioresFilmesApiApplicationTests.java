package br.com.gui.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.gui.api.dominio.dto.MinMaxDTO;
import br.com.gui.api.servico.LerPlanilhaServico;

@SpringBootTest(classes = PioresFilmesApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PioresFilmesApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private LerPlanilhaServico servico;

	@Test
	void contextLoads() {
		this.servico.extrairDadosPlanilha("movielist.csv");
	}

	@Test
	public void buscar_premiacao() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<MinMaxDTO> response = restTemplate.exchange(getUrlServidor() + "/premiacoes", HttpMethod.GET,
				entity, MinMaxDTO.class);

		assertThat(response.getBody() != null);
	}

	private String getUrlServidor() {
		return "http://localhost:" + port;
	}

}
