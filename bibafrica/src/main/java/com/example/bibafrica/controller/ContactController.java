package com.example.bibafrica.controller;
import com.example.bibafrica.model.Contact;
import com.example.bibafrica.services.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @GetMapping("/contact")
    public String viewContact(Model model){
        model.addAttribute("contact", new Contact());
        return "contactus";
    }
    @GetMapping("/display")
    public String displayContact(Model model){
        model.addAttribute("display",contactService.GetAllContact());
        return "displayContactus";
    }
    @PostMapping("/regContactus")
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        Contact contact1 = contactService.saveVontact(contact);
        if (contact1 != null) {
            return "redirect:/contact?success";
        } else {
            return "redirect:/contact?error";
        }
    }
    @GetMapping("/display/{emaill}")
public String deleteContact(@PathVariable String emaill){
        contactService.deleteContact(emaill);
        return "redirect:/display";
    }
}
