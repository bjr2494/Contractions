package day8.app;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping
public class ContractionController {
	
	@Autowired
	ContractionRepository repository;

	@GetMapping("/contractions")
	public String contractions(Model model) {
		model.addAttribute("contractions", repository.findAll());
		return "contractions";
	}
	
	@GetMapping("/contractions/{id}")
	public String singleContraction(@PathVariable("id") Long id, Model model) {
		Optional<Contraction> optionalContraction = repository.findById(id);
			if (optionalContraction.isPresent()) {
				model.addAttribute("contraction", optionalContraction.get());
				return "singleContraction";
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/contractions/new")
	public String newContraction(Model model) {
		Contraction contraction = new Contraction();
		model.addAttribute("contraction", contraction);
		return "newContraction";
	}
	
	@PostMapping("/contractions/new")
	public String saveContraction(@Valid Contraction contraction, Errors errors) {
		if (errors.hasErrors()) {
			return "newContraction";
		}
		else {
			repository.save(contraction);
			return "redirect:/contractions";
		}	
	}
	
	@GetMapping("/contractions/alter/{id}")
	public String alterContraction(@PathVariable("id") Long id, Model model) {
		Optional<Contraction> optionalContraction = repository.findById(id);
			if (optionalContraction.isPresent()) {
				model.addAttribute("contraction", optionalContraction.get());
				return "alterContraction";
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/contractions/alter/{id}")
	public String savedAlteredContraction(@PathVariable("id") Long id, @Valid Contraction contraction,
				Errors errors) {
			if (errors.hasErrors())
				return "alterContraction";
			else {
				repository.save(contraction);
				return "redirect:/contractions";
			}
	}
	
	@PostMapping("/contractions/delete/{id}")
	public String deleteContraction(@PathVariable Long id) {
		Optional<Contraction> optionalContraction = repository.findById(id);
		if (optionalContraction.isPresent()) {
			repository.delete(optionalContraction.get());
			return "redirect:/contractions";
		}
		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
