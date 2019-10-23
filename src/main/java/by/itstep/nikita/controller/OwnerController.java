package by.itstep.nikita.controller;

import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.domain.Owner;
import by.itstep.nikita.service.LiftService;
import by.itstep.nikita.service.OwnerService;
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
import java.util.Set;

@Controller
public class OwnerController {
    @Autowired
    OwnerService ownerService;
    @Autowired
    LiftService liftService;

    @GetMapping("owners")
    public String listOfOwners(Model model,
                               @RequestParam(required = false, defaultValue = "") String filter,
                               @RequestParam(required = false, defaultValue = "") String filterBy,
                               @RequestParam(required = false, defaultValue = "") Owner removeOwner,
                               @RequestParam(required = false, defaultValue = "") Owner fixOwner,
                               @RequestParam(required = false, defaultValue = "") Owner ownersLifts,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Owner> page;
        if (filter != null && !filter.isEmpty()) {
            page = ownerService.getByFilter(filterBy, filter, pageable);
        } else {
            page = ownerService.getAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/owner");
        model.addAttribute("filter", filter);


        /*         Remove Owner          */
        if (removeOwner != null) {
            ownerService.remove(removeOwner);
        }

        /*         Fix Owner          */

        if (fixOwner != null) {
            ownerService.fixOwner(fixOwner);
        }
        return "owners";
    }

    @GetMapping("editOwner")
    public String addOwner() {
        return "editOwner";
    }

    @GetMapping("editOwner/{id}")
    public String updateOwner(Model model,
                              @PathVariable Long id,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Owner editOwner = ownerService.getById(id);
        Page<Owner> page = ownerService.getAll(pageable);
        model.addAttribute("page", page);
        if (editOwner != null) {
            model.addAttribute("owner", editOwner);
        }
        return "editOwner";
    }

    @PostMapping("editOwner")
    public String addOwner(@Valid Owner owner, BindingResult bindingResult, Model model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return getOwners(owner, bindingResult, model, pageable);
    }

    @PostMapping("editOwner/{id}")
    public String updateOwner(@Valid Owner owner, BindingResult bindingResult, Model model,
                              @PathVariable Long id,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return getOwners(owner, bindingResult, model, pageable);
    }

    private String getOwners(@Valid Owner owner, BindingResult bindingResult, Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Owner> page = ownerService.getAll(pageable);
        model.addAttribute("url", "/owners");
        model.addAttribute("page", page);
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", page);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("oneOwner", owner);
            return "editOwner";
        } else {
            if (ownerService.saveOwner(owner)) {
                return "redirect:/owners";
            } else {
                model.addAttribute("page", page);
                model.addAttribute("savingReport", "Владелец с таким названием уже существует в базе!");
                model.addAttribute("oneOwner", owner);
                return "editOwner";
            }
        }
    }


}
