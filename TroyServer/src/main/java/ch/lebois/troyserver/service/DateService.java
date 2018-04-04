package ch.lebois.troyserver.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.stereotype.Service;

/**
 * @author Felix
 * @date 03.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.service
 **/
@Service
public class DateService {

    public String getDate() {
        return new SimpleDateFormat("HH:mm dd.MM").format(Calendar.getInstance().getTime());
    }

}
