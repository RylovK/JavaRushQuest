package co.myproject.javarushquest.controller;

import co.myproject.javarushquest.model.PlayerInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/")
public class QuestController {

    @GetMapping
    public String index(HttpSession session, HttpServletRequest request, Model model) {
        PlayerInfo playerInfo = (PlayerInfo) session.getAttribute("playerInfo");
        if (playerInfo == null) {
            String ip = request.getRemoteAddr();
            session.setAttribute("playerInfo", new PlayerInfo("Player", ip));
        }
        model.addAttribute("playerInfo", session.getAttribute("playerInfo"));
        return "index";
    }

    @PostMapping("/start")
    public String startGame(@RequestParam String playerName, HttpSession session) {
        PlayerInfo playerInfo = (PlayerInfo) session.getAttribute("playerInfo");
        if (playerInfo != null) {
            if (playerInfo.getName().equals(playerName)) {
                playerInfo.incrementGamesPlayed();
            } else {
                String ip = playerInfo.getIp();
                session.setAttribute("playerInfo", new PlayerInfo(playerName, ip));
            }
        }
        return "redirect:/quests/1";
    }

    @GetMapping("/quests/{id}")
    public String getQuestion(@PathVariable String id, HttpSession session, Model model) {
        model.addAttribute("playerInfo", session.getAttribute("playerInfo"));
        return "quests/" + id;
    }
}