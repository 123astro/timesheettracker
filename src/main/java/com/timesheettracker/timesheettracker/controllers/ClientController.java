package com.timesheettracker.timesheettracker.controllers;


import com.timesheettracker.timesheettracker.models.Client;
import com.timesheettracker.timesheettracker.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController


@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/")
    public ResponseEntity<?> createNewClient(@RequestBody Client newClient){
        Client client = clientRepository.save(newClient);
                return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientByID(@PathVariable Long id){
        Optional<Client> maybeClient = clientRepository.findById(id);
        if (maybeClient.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maybeClient.get(), HttpStatus.OK);

    }


}
