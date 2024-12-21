package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Передаем пустой объект пользователя в модель для привязки формы
        model.addAttribute("user", new ApplicationUser());
        return "register"; // Отображаем шаблон register.html
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("user") ApplicationUser user,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        // Если есть ошибки валидации, возвращаемся на форму с ошибками
        if (bindingResult.hasErrors()) {
            logger.warn("Ошибки валидации при регистрации пользователя: {}", user.getUsername());

            // Передаем ошибку в RedirectAttributes
            redirectAttributes.addFlashAttribute("registrationError", "Пожалуйста, исправьте ошибки в форме.");
            return "redirect:/register"; // Редирект на форму с ошибками
        }

        // Проверяем, существует ли пользователь с таким логином и ролью
        if (userRepository.existsByUsernameAndRole(user.getUsername(), user.getRole())) {
            logger.warn("Попытка регистрации с уже существующим логином и ролью: {} ({})", user.getUsername(), user.getRole());

            // Передаем ошибку в RedirectAttributes
            redirectAttributes.addFlashAttribute("registrationError", "Пользователь с данным логином и ролью уже существует.");
            return "redirect:/register"; // Редирект на форму с ошибками
        }

        try {
            // Кодируем пароль перед сохранением
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Сохраняем пользователя в базе данных
            userRepository.save(user);
            logger.info("Пользователь успешно зарегистрирован: {}", user.getUsername());

            // Перенаправляем на страницу логина с успехом
            redirectAttributes.addFlashAttribute("registrationSuccess", "Регистрация прошла успешно! Теперь вы можете войти.");
            return "redirect:/register"; // Редирект на форму с успешным сообщением
        } catch (Exception e) {
            // Логируем ошибку сохранения пользователя
            logger.error("Ошибка при сохранении пользователя: {}", user.getUsername(), e);

            // Передаем ошибку в RedirectAttributes
            redirectAttributes.addFlashAttribute("registrationError", "Произошла ошибка при регистрации. Пожалуйста, попробуйте снова.");
            return "redirect:/register"; // Редирект на форму с ошибкой
        }
    }
}