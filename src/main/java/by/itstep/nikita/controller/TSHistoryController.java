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
import org.springframework.web.bind.annotation.*;

@Controller
public class TSHistoryController {
    @Autowired
    TechServHistoryService techServHistoryService;


    @GetMapping("history")
    public String showHistories(Model model,
                                @RequestParam(required = false, defaultValue = "") TechServiceHistory done,
                                @RequestParam(required = false, defaultValue = "") TechServiceHistory notDone,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        if (done != null) {
            techServHistoryService.setDone(done);
        }

        if (notDone != null) {
            techServHistoryService.setNotDone(notDone);
        }

        Page<TechServiceHistory> page = techServHistoryService.getAll(pageable);
        model.addAttribute("page", page);
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

    @GetMapping("techHistory/{id}")
    public String showLiftHistory(Model model,
                                  @PathVariable Long id,

                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TechServiceHistory> liftHistories = techServHistoryService.getLiftHistories(id, pageable);
        model.addAttribute("page", liftHistories);
        model.addAttribute("url", "/techHistory");

        return "techHistory";
    }
}

