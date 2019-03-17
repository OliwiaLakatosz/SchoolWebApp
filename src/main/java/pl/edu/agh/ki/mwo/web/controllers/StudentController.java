package pl.edu.agh.ki.mwo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.ki.mwo.model.School;
import pl.edu.agh.ki.mwo.persistence.DatabaseConnector;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

@Controller
public class StudentController {

    @RequestMapping(value = "/Students")
    public String listStudents(Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        model.addAttribute("students", DatabaseConnector.getInstance().getStudents());

        return "studentsList";
    }

    @RequestMapping(value = "/DeleteStudent", method = RequestMethod.POST)
    public String deleteStudent(@RequestParam(value = "studentId", required = false) String studentId,
                                Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        DatabaseConnector.getInstance().deleteStudent(studentId);
        model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
        model.addAttribute("message", "Uczeń został usunięty");

        return "studentsList";
    }
}
