package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "reciver/")
public class ReciverController {

    private final Logger log = LoggerFactory.getLogger(ReciverController.class);

    private FileService fileService;


    ReciverController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getInfo(@PathVariable(value = "client") String client,
                          @RequestParam(name = "value", defaultValue = "") String value) {
        log.info(value);
        fileService.setFilePath("logs\\client_" + client + ".log");
        fileService.write(value);
        return "receiver";
    }


}
