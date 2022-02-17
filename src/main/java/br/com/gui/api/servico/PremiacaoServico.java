package br.com.gui.api.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gui.api.dominio.Filme;
import br.com.gui.api.dominio.Produtor;
import br.com.gui.api.dominio.dto.MinMaxDTO;
import br.com.gui.api.dominio.dto.VitoriaProdutorDTO;
import br.com.gui.api.repositorio.ProdutorRepositorio;

@Service
public class PremiacaoServico {

	private ProdutorRepositorio produtorRepo;

	public PremiacaoServico(ProdutorRepositorio produtorRepo) {
		this.produtorRepo = produtorRepo;
	}

	public MinMaxDTO buscarVitoriosos() {
		List<Produtor> produtores = this.produtorRepo.somenteProdutoresComVitorias();
		List<VitoriaProdutorDTO> lista = new ArrayList<>();

		for (Produtor p : produtores) {
			VitoriaProdutorDTO dto = new VitoriaProdutorDTO(p.getNome(), 0, 9999, 0);

			for (Filme f : p.getFilmes()) {
				if (f.getVencedor()) {
					if (f.getAno() < dto.getPreviousWin()) {
						dto.setPreviousWin(f.getAno());
					}

					if (f.getAno() > dto.getFollowingWin()) {
						dto.setFollowingWin(f.getAno());
					}
				}
			}

			if (dto.getInterval() > 0) {
				lista.add(dto);
			}
		}

		MinMaxDTO respostaDTO = new MinMaxDTO();
		Collections.sort(lista);
		respostaDTO.setMin(this.processarResultado(lista));
		
		Collections.sort(lista, Collections.reverseOrder());
		respostaDTO.setMax(this.processarResultado(lista));

		return respostaDTO;
	}
	
	private List<VitoriaProdutorDTO> processarResultado(List<VitoriaProdutorDTO> lista){
		List<VitoriaProdutorDTO> resultado = new ArrayList<>();
		int intervalo = lista.get(0).getInterval();
		for (VitoriaProdutorDTO dto : lista) {
			if(intervalo == dto.getInterval()) {
				resultado.add(dto);
			}else {
				break;
			}
		}
		return resultado;
	}
}