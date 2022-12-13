package controllers;
import org.springframework.web.bind.annotation.*;
import service.TurnService;



@RestController
@RequestMapping("/turns")
public class TurnController {

    public final TurnService turnService;

    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }
}
