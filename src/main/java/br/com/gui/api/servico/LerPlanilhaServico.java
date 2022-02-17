package br.com.gui.api.servico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.gui.api.dominio.Filme;
import br.com.gui.api.dominio.Produtor;
import br.com.gui.api.repositorio.FilmeRepositorio;
import br.com.gui.api.repositorio.ProdutorRepositorio;

@Service
public class LerPlanilhaServico {
	
	private FilmeRepositorio filmeRepo;
	
	private ProdutorRepositorio produtorRepo;

	public LerPlanilhaServico(FilmeRepositorio filmeRepo, ProdutorRepositorio produtorRepo) {
		this.filmeRepo = filmeRepo;
		this.produtorRepo = produtorRepo;
	}
	
	public void extrairDadosPlanilha(String caminhoArquivo) {
		if(caminhoArquivo == null || caminhoArquivo.isEmpty()) {
			return;
		}
		
		BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ";";
	    List<Filme> filmes = new ArrayList<>();
	    
	    try {

	        br = new BufferedReader(new FileReader(caminhoArquivo));
	        while ((linha = br.readLine()) != null) {
	        	String[] dados = linha.split(csvDivisor);
	        	if(!dados[0].equals("year")) {
	        		Filme filme = new Filme(null, Integer.valueOf(dados[0]), dados[1], dados[2], false);
	        		filme.setProdutores(this.salvarProdutores(dados[3]));
	        		if(dados.length == 5) {
	        			filme.setVencedor(true);
	        		}
	        		this.produtorRepo.saveAll(filme.getProdutores());
	        		filmes.add(filme);
	        	}

	        }
	        
	        this.filmeRepo.saveAll(filmes);
	        

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	private List<Produtor> salvarProdutores(String nomeProdutores){
		List<Produtor> produtores = new ArrayList<>();
		
		nomeProdutores = nomeProdutores.replace(" and ", ",").replace(",,", ",");
		
		for(int i = 0; i < nomeProdutores.split(",").length; i++) {
			String nome = nomeProdutores.split(",")[i].trim();
			
			Optional<Produtor> optProdutor = this.produtorRepo.findFirstByNome(nome);
			
			if(optProdutor.isPresent()) {
				produtores.add(optProdutor.get());
			}else {
				Produtor produtor = this.produtorRepo.save(new Produtor(null, nome)); 
				produtores.add(produtor);
			}
		}
		return produtores;
	}

}
