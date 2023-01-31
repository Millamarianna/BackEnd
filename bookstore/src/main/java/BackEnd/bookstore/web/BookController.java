package BackEnd.bookstore.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class BookController {
	
	@GetMapping("index")
	@ResponseBody
	public String returnIndex() {
		return "This is the main page";
	}
}