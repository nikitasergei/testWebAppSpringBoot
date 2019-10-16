package by.itstep.nikita.controller;

import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.service.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class LiftController {
    @Autowired
    LiftService liftService;

    @GetMapping("lifts")
    public String listOfLifts(Model model,
                              @RequestParam(required = false, defaultValue = "") String filter,
                              @RequestParam(required = false, defaultValue = "") String filterBy,
                              @RequestParam(required = false, defaultValue = "") Lift removeLift,
                              @RequestParam(required = false, defaultValue = "") Lift fixLift,
                              @PageableDefault(sort = {"address"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Lift> page;
        if (filter != null && !filter.isEmpty())
            page = liftService.getByFilter(filterBy, filter, pageable);
        else page = liftService.getAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/lifts");
        model.addAttribute("filter", filter);

        /*         Remove Lift          */
        if (removeLift != null) {
            liftService.remove(removeLift);
        }

        /*         Fix Lift          */

        if (fixLift != null) {
            liftService.fixLift(fixLift);
        }
        return "lifts";
    }

    @GetMapping("editLift")
    public String addLift() {
        return "editLift";
    }

    @GetMapping("editLift/{id}")
    public String updateLift(Model model,
                             @PathVariable Long id,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Lift editLift = liftService.getById(id);
        Page<Lift> page = liftService.getAll(pageable);
        model.addAttribute("page", page);
        if (editLift != null) {
            model.addAttribute("lift", editLift);
        }
        return "editLift";
    }

    @PostMapping("editLift")
    public String addLift(@Valid Lift lift, BindingResult bindingResult, Model model,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {


        return getLifts(lift, bindingResult, model, pageable);
    }

    @PostMapping("editLift/{id}")
    public String updateLift(@Valid Lift lift, BindingResult bindingResult, Model model,
                             @PathVariable Long id,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return getLifts(lift, bindingResult, model, pageable);
    }


    // Где должен быть этот метод?
    private String getLifts(@Valid Lift lift, BindingResult bindingResult, Model model,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Lift> page = liftService.getAll(pageable);
        model.addAttribute("url", "/lifts");
        model.addAttribute("page", page);
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("oneLift", lift);
            return "editLift";
        } else {
            if (liftService.saveLift(lift)) {
                return "redirect:/lifts";
            } else {
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "Лифт с таким регистрационным или заводским номером уже существует");
                model.addAttribute("oneLift", lift);
                return "editLift";
            }
        }
    }
}
