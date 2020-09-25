package pl.pzdev2.virtua;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VirtuaLogsControler {
	
	VirtuaLogsRepository virtuaLogsRepository;

	public VirtuaLogsControler(VirtuaLogsRepository virtuaLogsRepository) {
		this.virtuaLogsRepository = virtuaLogsRepository;
	}
	
	@GetMapping("/virtuaLogs")
	String getVirtuaLogs(Model model) {
		
		var virtuaLogs = virtuaLogsRepository.findAll();
		model.addAttribute("virtuaLogs", virtuaLogs);
		
		return "virtuaLogs";
	}
	
//	@GetMapping("/findVirtuaLogsByBarcode")
//	public List<VirtuaLogs> getByBarcode(@RequestParam String barcode) {
//		return virtuaLogsService.getByBarcode(barcode);
//	}

}
