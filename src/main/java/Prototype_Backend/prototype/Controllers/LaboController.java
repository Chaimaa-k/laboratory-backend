package Prototype_Backend.prototype.Controllers;


import Prototype_Backend.prototype.entities.Laboratoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Prototype_Backend.prototype.repositories.LaboRepository;

@RestController
@RequestMapping("/api/laboratories")
public class LaboController {

    @Autowired
    private LaboRepository laboratoireRepository;

    @PostMapping
    public Laboratoire createLaboratoire(@RequestBody Laboratoire laboratoire) {
        System.out.println("Laboratoire avant sauvegarde : " + laboratoire);
        return laboratoireRepository.save(laboratoire);
    }

}
