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
import ua.cardsmanager.dto.TrainingDto;
import ua.cardsmanager.model.Card;
import ua.cardsmanager.model.Category;
import ua.cardsmanager.service.CardsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cards")

public class CardsController {

    @Autowired
    private CardsService cardsService;


    @RequestMapping(value = "/cardsList", method = RequestMethod.GET)
    public String getList(Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            model.addAttribute("cardsList", cardsService.getAll(userId));
            request.getSession().setAttribute("lastPage", "/cards/cardsList/");
            return "cardsStartPage";
        } else return "loginFailed";

    }

    @RequestMapping(value = "/cardsList/{id}", method = RequestMethod.GET)
    public String getListSelected(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            String status = request.getParameter("status");
            List<Card> cardsList = status != null ? cardsService.getAllFromCategoryByStatus(id, userId, Boolean.valueOf(status)) : cardsService.getAllFromCategory(id, userId);
            model.addAttribute("cardsList", cardsList);
            request.getSession().setAttribute("lastPage", status != null ? "/cards/cardsList/" + id + "?status=" + status : "/cards/cardsList/" + id);
            return "cardsStartPage";
        } else return "loginFailed";

    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView(Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            List<Category> categoryList = cardsService.getAllCategoriesWithoutCards(userId);
            Category categoryGen = cardsService.getByCategoryName("general", userId);
            categoryList.remove(categoryGen);
            model.addAttribute("card", new Card());
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("general", categoryGen);
            return "cardsAddForm";
        } else return "loginFailed";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("card") @Validated Card card, BindingResult result, HttpServletRequest request, Model model) {
        Integer userId = getUserId(request);
        if (userId != null) {
            if (result.hasErrors()) {
                List<Category> categoryList = cardsService.getAllCategoriesWithoutCards(userId);
                Category categoryGen = cardsService.getByCategoryName("general", userId);
                categoryList.remove(categoryGen);
                model.addAttribute("categoryList", categoryList);
                model.addAttribute("general", categoryGen);
                return "cardsAddForm";
            }
            cardsService.create(card, userId);
            return "redirect:/cards/cardsList";
        } else {
            return "loginFailed";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editView(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            Card card = cardsService.get(id, userId);
            if (card != null) {
                model.addAttribute("card", card);
                List<Category> categoryList = cardsService.getAllCategoriesWithoutCards(userId);
                categoryList.remove(card.getCategory());
                model.addAttribute("categoryList", categoryList);
                return "cardsEditForm";
            }
            return "loginFailed";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("card") @Validated Card card, BindingResult result, HttpServletRequest request, Model model) {
        Integer userId = getUserId(request);
        if (userId != null) {
            if (result.hasErrors()) {
                List<Category> categoryList = cardsService.getAllCategoriesWithoutCards(userId);
                categoryList.remove(card.getCategory());
                model.addAttribute("card", card);
                model.addAttribute("categoryList", categoryList);
                return "cardsEditForm";
            }
            cardsService.update(card, userId);
            String url = (String) request.getSession().getAttribute("lastPage");
            return "redirect:" + url;
        } else {
            return "loginFailed";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            cardsService.delete(id, userId);
            String url = (String) request.getSession().getAttribute("lastPage");
            return "redirect:" + url;
        } else {
            return "loginFailed";
        }
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.GET)
    public String updateStatus(@PathVariable("id") Integer id, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            Card card = cardsService.get(id, userId);
            String status = request.getParameter("status");
            card.setDone(!status.equals("true"));
            Card cardUpdated = cardsService.update(card, userId);
            cardsService.changeStatus(cardUpdated);
            String url = (String) request.getSession().getAttribute("lastPage");
            return "redirect:" + url;
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/training", method = RequestMethod.GET)
    public String getTraining(Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            model.addAttribute("training", new TrainingDto());
            return "trainingStartPage";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/training", method = RequestMethod.POST)
    public String postTraining(@ModelAttribute("training") TrainingDto trainingDto, Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        if (userId != null) {
            if (trainingDto.getTraining() == null) {
                return "redirect:/cards/training";
            }
            Map<Category, Integer> map = cardsService.getCategorySortedList(userId, trainingDto.getTraining());

            if (map.isEmpty()) {
                model.addAttribute("error", "There is no cards for chosen training. Please try another training");
                model.addAttribute("training", new TrainingDto());
                return "trainingStartPage";
            }

            if (trainingDto.getCategories() == null || trainingDto.getCategories().isEmpty()) {
                model.addAttribute("training", trainingDto);
                model.addAttribute("categoryList", map);
                return "trainingStartPage";
            }
            List<Card> cardList = cardsService.getSortedCardList(userId, trainingDto);
            if (cardList.size() == 0) {
                model.addAttribute("error", "There is no cards for chosen training. Please try another training");
                model.addAttribute("training", new TrainingDto());
                return "trainingStartPage";
            }
            request.getSession().setAttribute("cardsList", cardList);
            request.getSession().setAttribute("training", trainingDto.getTraining());
            model.addAttribute("cardsList", cardList);
            model.addAttribute("trainingName", trainingDto.getTraining());
            return "training";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/training/{id}")
    public String postTraining(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Integer userId = getUserId(request);
        String done = request.getParameter("status");
        if (userId != null) {
            Card card = cardsService.get(id, userId);
            String training = (String) request.getSession().getAttribute("training");
            if (card == null || training == null) {
                return "redirect:/cards/training";
            }
            if (done != null && done.equals("true")) {
                cardsService.updateStatus(card, training, userId);
            }
            @SuppressWarnings("unchecked")
            List<Card> cardList = (ArrayList<Card>) request.getSession().getAttribute("cardsList");
            cardList.remove(card);

            if (cardList.size() == 0) {
                return "redirect:/cards/training";
            }

            request.getSession().setAttribute("cardsList", cardList);
            model.addAttribute("cardsList", cardList);
            model.addAttribute("trainingName", training);
            return "training";
        }
        return "loginFailed";
    }

    /*private boolean isAllowed(HttpServletRequest request, Integer id) {

        HttpSession session = request.getSession(true);

        return !session.isNew() && session.getAttribute("userId") != null && session.getAttribute("userId").equals(id);
    }*/

    private Integer getUserId(HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        return !session.isNew() && session.getAttribute("userId") != null ? (Integer) session.getAttribute("userId") : null;
    }
}
