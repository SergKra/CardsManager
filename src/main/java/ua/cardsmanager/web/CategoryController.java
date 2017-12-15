package ua.cardsmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cardsmanager.model.Category;
import ua.cardsmanager.service.CardsService;
import ua.cardsmanager.util.HelperUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {


    @Autowired
    private CardsService cardsService;

    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public String getCategoryList(Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            List<Category> categories = cardsService.getAllCategories(userId);
            model.addAttribute("categoryList", HelperUtil.getCategoryListWithSize(categories));
            return "categoriesStartPage";
        } else return "loginFailed";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            model.addAttribute("category", new Category());
            return "categoryAddForm";
        } else return "loginFailed";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("category") @Validated Category category, BindingResult result, HttpServletRequest request, Model model) {
        Integer userId = getUserId(request);
        if (userId != null) {
            if (result.hasErrors()) {
                return "categoryAddForm";
            }
            cardsService.create(category, userId);
            return "redirect:/category/categoryList";
        } else {
            return "loginFailed";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            Category category = cardsService.getCategory(id, userId);
            if (category != null) {
                model.addAttribute("category", category);
                return "categoryEditForm";
            }
            return "loginFailed";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("category") @Validated Category category, BindingResult result, HttpServletRequest request, Model model) {
        Integer userId = getUserId(request);
        if (userId != null) {
            if (result.hasErrors()) {
                model.addAttribute("category", category);
                return "categoryEditForm";
            }
            cardsService.updateCategory(category, userId);
            return "redirect:/category/categoryList";
        } else {
            return "loginFailed";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            cardsService.deleteCategory(id, userId);
            return "redirect:/category/categoryList";
        } else {
            return "loginFailed";
        }
    }

    private Integer getUserId(HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        return !session.isNew() && session.getAttribute("userId") != null ? (Integer) session.getAttribute("userId") : null;
    }
}
