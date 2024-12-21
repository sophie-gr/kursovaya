package com.example.demo;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplyService supplyService;
    @Autowired
    private EmployeeService employeeService;

    // Main page with sorting and filtering
    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sortBy,
            Model model
    ) {
        List<Product> listProducts = service.listAll(keyword);

        // Sorting logic
        if (sortBy != null) {
            switch (sortBy) {
                case "nameAsc":
                    listProducts.sort(Comparator.comparing(Product::getName));
                    break;
                case "nameDesc":
                    listProducts.sort(Comparator.comparing(Product::getName).reversed());
                    break;
                case "priceAsc":
                    listProducts.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "priceDesc":
                    listProducts.sort(Comparator.comparing(Product::getPrice).reversed());
                    break;
                case "quantityAsc":
                    listProducts.sort(Comparator.comparing(Product::getQuantity));
                    break;
                case "quantityDesc":
                    listProducts.sort(Comparator.comparing(Product::getQuantity).reversed());
                    break;
            }
        }

        // Add additional lists to the model
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listCustomers", customerService.listAll(null));
        model.addAttribute("listOrders", orderService.listAll(null));
        model.addAttribute("listEmployees", employeeService.listAll(null));
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);

        return "index";
    }

    // Product CRUD operations
    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("product", service.findById(id));
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/";
    }

    // Customer CRUD operations
    @GetMapping("/customers")
    public String listCustomers(Model model,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "sortBy", required = false) String sortBy) {
        List<Customer> listCustomers;

        if (keyword != null && !keyword.isEmpty()) {
            listCustomers = customerService.findByKeyword(keyword);
        } else {
            listCustomers = customerService.listAll();
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "nameAsc":
                    listCustomers.sort(Comparator.comparing(Customer::getName));
                    break;
                case "nameDesc":
                    listCustomers.sort(Comparator.comparing(Customer::getName).reversed());
                    break;
                case "emailAsc":
                    listCustomers.sort(Comparator.comparing(Customer::getEmail));
                    break;
                case "emailDesc":
                    listCustomers.sort(Comparator.comparing(Customer::getEmail).reversed());
                    break;
            }
        }

        model.addAttribute("listCustomers", listCustomers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);

        return "customer";
    }

    @GetMapping("/customers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "newcustomer";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("editcustomer");
        mav.addObject("customer", customerService.findById(id));
        return mav;
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }

    // Orders CRUD operations
    @GetMapping("/orders")
    public String listOrders(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sortBy,
            Model model
    ) {
        List<Order> listOrders = orderService.listAll();

        if (keyword != null && !keyword.isEmpty()) {
            listOrders = orderService.findByKeyword(keyword);
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "dateAsc":
                    listOrders.sort(Comparator.comparing(Order::getOrderDate));
                    break;
                case "dateDesc":
                    listOrders.sort(Comparator.comparing(Order::getOrderDate).reversed());
                    break;
                case "amountAsc":
                    listOrders.sort(Comparator.comparing(Order::getTotalAmount));
                    break;
                case "amountDesc":
                    listOrders.sort(Comparator.comparing(Order::getTotalAmount).reversed());
                    break;
            }
        }

        model.addAttribute("listOrders", listOrders);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);

        return "order";
    }

    @GetMapping("/orders/new")
    public String newOrder(Model model) {
        model.addAttribute("order", new Order());
        return "neworder";
    }

    @PostMapping("/orders/save")
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public ModelAndView editOrder(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("editorder");
        mav.addObject("order", orderService.findById(id));
        return mav;
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }

    // Supplier CRUD operations
    @GetMapping("/suppliers")
    public String listSuppliers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sortBy,
            Model model
    ) {
        List<Supplier> listSuppliers = (keyword != null && !keyword.isEmpty())
                ? supplierService.findByKeyword(keyword)
                : supplierService.listAll(null);

        if (sortBy != null) {
            switch (sortBy) {
                case "nameAsc":
                    listSuppliers.sort(Comparator.comparing(Supplier::getName));
                    break;
                case "nameDesc":
                    listSuppliers.sort(Comparator.comparing(Supplier::getName).reversed());
                    break;
                case "contactAsc":
                    listSuppliers.sort(Comparator.comparing(Supplier::getContactInfo));
                    break;
                case "contactDesc":
                    listSuppliers.sort(Comparator.comparing(Supplier::getContactInfo).reversed());
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("listSuppliers", listSuppliers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);

        return "supplier";
    }

    @GetMapping("/suppliers/new")
    public String showNewSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "newsupplier";
    }

    @PostMapping("/suppliers/save")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/edit/{id}")
    public ModelAndView editSupplier(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("editsupplier");
        mav.addObject("supplier", supplierService.findByID(id));
        return mav;
    }

    @GetMapping("/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return "redirect:/suppliers";
    }

    @GetMapping("/supplies")
    public String listSupplies(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String startDate, // Начальная дата фильтрации
            @RequestParam(required = false) String endDate,   // Конечная дата фильтрации
            Model model
    ) {
        // Получаем список поставок, фильтруем по ключевому слову, если оно есть
        List<Supply> listSupplies = (keyword != null && !keyword.isEmpty())
                ? supplyService.findByKeyword(keyword)
                : supplyService.listAll(null);

        // Фильтрация по датам
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            try {
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = LocalDate.parse(endDate);
                listSupplies = listSupplies.stream()
                        .filter(supply -> !supply.getSupplyDate().isBefore(start) && !supply.getSupplyDate().isAfter(end))
                        .collect(Collectors.toList());
            } catch (DateTimeParseException e) {
                // Логика обработки ошибок парсинга дат
                model.addAttribute("error", "Неверный формат даты. Используйте формат YYYY-MM-DD.");
            }
        }

        // Сортировка
        if (sortBy != null) {
            switch (sortBy) {
                case "dateAsc":
                    listSupplies.sort(Comparator.comparing(Supply::getSupplyDate));
                    break;
                case "dateDesc":
                    listSupplies.sort(Comparator.comparing(Supply::getSupplyDate).reversed());
                    break;
                case "quantityAsc":
                    listSupplies.sort(Comparator.comparing(Supply::getQuantity));
                    break;
                case "quantityDesc":
                    listSupplies.sort(Comparator.comparing(Supply::getQuantity).reversed());
                    break;
                default:
                    break;
            }
        }

        // Передаем данные в модель
        model.addAttribute("listSupplies", listSupplies);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "supply";
    }

    @GetMapping("/supplies/new")
    public String showNewSupplyForm(Model model) {
        model.addAttribute("supply", new Supply());
        return "newsupply";
    }

    @PostMapping("/supplies/save")
    public String saveSupply(@ModelAttribute("supply") Supply supply) {
        supplyService.save(supply);
        return "redirect:/supplies";
    }

    @GetMapping("/supplies/edit/{id}")
    public ModelAndView editSupply(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("editsupply");
        mav.addObject("supply", supplyService.findByID(id));
        return mav;
    }

    @GetMapping("/supplies/delete/{id}")
    public String deleteSupply(@PathVariable Integer id) {
        supplyService.deleteById(id);
        return "redirect:/supplies";
    }


    // Employee CRUD operations
    @GetMapping("/employees")
    public String listEmployees(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            Model model
    ) {
        // Получение списка сотрудников
        List<Employee> listEmployees = (keyword != null && !keyword.isEmpty())
                ? employeeService.findByKeyword(keyword) // Поиск по ключевому слову
                : employeeService.listAll(keyword); // Все сотрудники

        // Логика сортировки
        if (sortBy != null) {
            switch (sortBy) {
                case "nameAsc":
                    listEmployees.sort(Comparator.comparing(Employee::getEmployeeName));
                    break;
                case "nameDesc":
                    listEmployees.sort(Comparator.comparing(Employee::getEmployeeName).reversed());
                    break;
                case "hireDateAsc":
                    listEmployees.sort(Comparator.comparing(Employee::getHireDate));
                    break;
                case "hireDateDesc":
                    listEmployees.sort(Comparator.comparing(Employee::getHireDate).reversed());
                    break;
                case "salaryAsc":
                    listEmployees.sort(Comparator.comparing(Employee::getSalary));
                    break;
                case "salaryDesc":
                    listEmployees.sort(Comparator.comparing(Employee::getSalary).reversed());
                    break;
            }
        }

        // Добавление данных в модель
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("keyword", keyword); // Ключевое слово для сохранения в форме
        model.addAttribute("sortBy", sortBy); // Текущая сортировка

        return "employee"; // Возвращение имени представления
    }

    @GetMapping("/employees/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "newemployee";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees"; // Исправил редирект на правильный путь
    }

    @GetMapping("/employees/edit/{id}")
    public ModelAndView editEmployee(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("editemployee");
        mav.addObject("employee", employeeService.findById(id));
        return mav;
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return "redirect:/employees"; // Исправил редирект на правильный путь
    }
    @GetMapping("/about-author")
    public String showAuthorPage() {
        return "author"; // Название View, соответствующее HTML-шаблону
    }
}
