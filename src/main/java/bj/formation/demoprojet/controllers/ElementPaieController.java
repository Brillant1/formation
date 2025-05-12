package bj.formation.demoprojet.controllers;


import bj.formation.demoprojet.dtos.ElementPaieRequestDto;
import bj.formation.demoprojet.services.ElementPaieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/element-paies")
@RequiredArgsConstructor
@Validated
public class ElementPaieController {

    private final ElementPaieService elementPaieService;
    @PostMapping
    public ResponseEntity<Object> saveElementPaie(@Valid @RequestBody ElementPaieRequestDto request){
        return elementPaieService.saveElementPaie(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return elementPaieService.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getElementPaieById(@PathVariable String code){
        return elementPaieService.findElementPaieById(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Object> updateElementPaie(@PathVariable String code, @RequestBody ElementPaieRequestDto elementPaieDetails){
        return elementPaieService.updateElementPaie(code, elementPaieDetails);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteElementPaie(@PathVariable String code){
        return elementPaieService.deleteElementPaie(code);
    }

}
