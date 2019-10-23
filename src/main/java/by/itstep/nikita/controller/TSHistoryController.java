package by.itstep.nikita.controller;

import by.itstep.nikita.domain.TechServiceHistory;
import by.itstep.nikita.service.TechServHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TSHistoryController {
    @Autowired
    TechServHistoryService techServHistoryService;

    @GetMapping("history")
    public String allHistory(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<TechServiceHistory> histories = techServHistoryService.getAll(pageable);
        model.addAttribute("page", histories);
        model.addAttribute("url", "/history");
        return "history";
    }

    @GetMapping("editHistory")
    public String addHistory() {
        return "editHistory";
    }

    @PostMapping("editHistory")
    public String addHistory(@ModelAttribute TechServiceHistory techServiceHistory,
                             Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return getString(techServiceHistory, model, pageable);
    }


    private String getString(@ModelAttribute TechServiceHistory techServiceHistory, Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TechServiceHistory> page = techServHistoryService.getAll(pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/history");

        if (techServHistoryService.addTechServiceHistory(techServiceHistory)) {
            return "redirect:/history";
        } else {
            model.addAttribute("page", page);
            model.addAttribute("savingReport", "Fatal Error!!!");
            model.addAttribute("usingHistory", techServHistoryService);
            return "history";
        }
    }
}

