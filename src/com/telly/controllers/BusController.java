package com.telly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BusController {
	@RequestMapping(value = "/createreserve", method = RequestMethod.POST)
	public String createReserve(@Validated(FormValidationGroup.class) Bus bus, BindingResult result,
								Principal principal) {

		if (result.hasErrors()) {
			return "reservebus";
		}

		busService.create(bus);

		return "home";

	}

	@RequestMapping("/results")
	public String leave(Model model, Principal principal) {

		model.addAttribute("bus", new Bus());

		return "results";
	}


	@RequestMapping(value = "/resultsfrom", method = RequestMethod.GET)
	public String leaveFrom(@Validated(FormValidationGroup.class) Bus bus, BindingResult result, Model model,
							Principal principal) {

		List<Bus> results = busService.getCity(bus.getLeaveFrom(), bus.getGoingTo(), bus.getDateLeave(),
				bus.getDateReturn());
		model.addAttribute("results", results);
		System.out.println(results);

		return "results";

	}

}
