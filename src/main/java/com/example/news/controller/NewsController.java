package com.example.news.controller;

import com.example.news.model.Article;
import com.example.news.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NewsController {

    private List<Article> articles = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public NewsController() {
        users.add(new User("admin", "1234"));
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())
                && u.getPassword().equals(user.getPassword())) {
                return "Đăng nhập thành công";
            }
        }
        return "Sai tài khoản hoặc mật khẩu";
    }

    @PostMapping("/articles")
    public String addArticle(@RequestBody Article article) {
        articles.add(article);
        return "Tin tức đã được thêm thành công";
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articles;
    }

    @GetMapping("/articles/search")
    public List<Article> searchArticles(@RequestParam String keyword) {
        List<Article> result = new ArrayList<>();
        for (Article art : articles) {
            if (art.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(art);
            }
        }
        return result;
    }
}
