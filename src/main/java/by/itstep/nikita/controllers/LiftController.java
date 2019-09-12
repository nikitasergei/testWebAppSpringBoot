package by.itstep.nikita.controllers;


import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.services.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("lifts")
public class LiftController {
    @Autowired
    LiftService liftService;

    @GetMapping
    public String listOfLifts(Model model,
                             @RequestParam(required = false, defaultValue = "") Lift editLift,
                             @RequestParam(required = false, defaultValue = "") Lift removeLift,
                             @RequestParam(required = false, defaultValue = "") Lift fixLift,
                             @PageableDefault(sort = {"factNum"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Lift> page = liftService.getAll(pageable);
        model.addAttribute("url", "/lifts");
        model.addAttribute("page", page);

        /*        Edit exist Lift        */
        if (editLift != null) {
            model.addAttribute("oneLift", editLift);
        }

        /*         remove Lift          */
        if (removeLift != null) {
            liftService.remove(removeLift);
        }

        /*         fix Lift          */

        if (fixLift != null) {
            liftService.fixLift(fixLift);
        }
        return "lifts";
    }
    @PostMapping
    public String addOrUpdateLift(@Valid Lift lift, BindingResult bindingResult, Model model,
                                 @PageableDefault(sort = {"factNum"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Lift> page = liftService.getAll(pageable);
        model.addAttribute("url", "/lifts");
        model.addAttribute("page", page);
        if (bindingResult.hasErrors()) {
//            model.addAttribute("liftsList", page);
            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("oneLift", lift);
            return "lifts";
        } else {
            if (liftService.saveLift(lift)) {
                return "redirect:lifts";
            } else {
                model.addAttribute("liftsList", page);
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "Lift with such registration number is already exists!");
                model.addAttribute("oneLift", lift);
                return "lifts";
            }
        }
    }
}
