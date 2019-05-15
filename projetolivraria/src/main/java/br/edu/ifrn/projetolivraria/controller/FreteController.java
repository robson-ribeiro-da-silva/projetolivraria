package br.edu.ifrn.projetolivraria.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifrn.projetolivraria.model.CalcPrazo;
import br.edu.ifrn.projetolivraria.model.Endereco;
import br.edu.ifrn.projetolivraria.model.Frete;
import br.edu.ifrn.projetolivraria.service.FreteService;


@Controller
@RequestMapping("/frete")
public class FreteController {
	
	@Autowired
	private FreteService service;
	
	@GetMapping("/add")
	public ModelAndView add(Frete frete) {
		Double valor = 5.0;
		frete.setValor(valor);
		
		ModelAndView mv = new ModelAndView("/frete/form");
		mv.addObject("frete", frete);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Frete frete, BindingResult result) throws JsonProcessingException {
		
		if(result.hasErrors()) {
			return add(frete);
		}
		
		/*ObjectMapper mapper = new ObjectMapper();
		
		CalcPrazo calcprazo = new CalcPrazo();
		calcprazo.setnCdServico("40010");
		calcprazo.setsCepOrigem("37410220");
		calcprazo.setsCepDestino("05311900");
		
		String jsonString = mapper.writeValueAsString(calcprazo);
		
		RestTemplate template = new RestTemplate();
		CalcPrazo calcprazo1 = template.getForObject(" ", CalcPrazo.class, jsonString);
		
		System.out.println(calcprazo1);*/
		
		/*String cep = frete.getCepDestino();
		
		RestTemplate template = new RestTemplate();
		
		Endereco endereco = template.getForObject("https://viacep.com.br/ws/"+cep+"/json",Endereco.class);
		
		if(endereco != null){			
			frete.setEstado(endereco.getUf());
			frete.setCidade(endereco.getLocalidade());
		}
		
		frete.setValor(5.0);
		frete.setPeso(1.0);
		//frete.setDataEntregaCliente(new Date());
		frete.setDataEntregaCorreios(new Date());
		frete.setCepOrigem("59910000");*/
		
		frete.setDataEntregaCorreios(new Date());
		
		service.save(frete);
		
		return details(frete.getId());
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("frete/listar");
		mv.addObject("fretes", service.findAll());
		
		return mv;
	}
	
	
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("frete/listar");
		mv.addObject("frete", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/alldetails/{id}")
	public ModelAndView alldetails(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("frete/detalhes");
		mv.addObject("frete", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/detailsByPedido/{id}")
	public ModelAndView detailsByPedido(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("frete/detailsByPedido");
		mv.addObject("frete", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}
