package ru.mirea.coursework.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
@Service
@RequiredArgsConstructor
//@EnableScheduling
public class SchedulerService {
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        System.out.println("The time is now " + dateFormat.format(new Date()));
//    }
    private final ItemService gameService;
    private final String path = "scheduler";

    //every minute
    @Scheduled(cron = "0 * * * * *")
    @ManagedOperation(
            description = "Deletes all contents of the directory and " +
                    "writes all data from the database to it every 30 minutes"
    )
    public void schedule() throws IOException, NullPointerException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        log.info("SCHEDULED task started at {}", formatter.format(date));

        File dir = ResourceUtils.getFile(path);
        Arrays.stream(Objects.requireNonNull(dir.listFiles())).forEach(file -> {
            if (file.isFile()) {
                log.info("File " + file.getName() + " was deleted: " + file.delete());
            }
        });


        BufferedWriter iBufWriter = createWriter(path, "levels.txt");;
        iBufWriter.write("id|name|complexity\n");
//        levelService.readAll().forEach(level ->
//        {
//            try{
//                iBufWriter.write(
//                        String.format(
//                                "%d|%s|%s\n",
//                                level.getId(),
//                                level.getName(),
//                                level.getComplexity()


























//                        )
//                );
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        });
        iBufWriter.close();

        BufferedWriter oBufWriter = createWriter(path, "games.txt");
        oBufWriter.write("id|name|creationDate\n");
        gameService.readAll()
                .forEach(game -> {
                    try {
                        oBufWriter.write(
                                String.format(
                                        "%d|%s|%s\n",
                                        game.getId(),
                                        game.getName(),
                                        game.getPrice())
                        );
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                });
        oBufWriter.close();
        log.info("SCHEDULED task finished.");
    }

    private BufferedWriter createWriter(String dir, String filename) throws IOException {
        return new BufferedWriter(new FileWriter(String.format("%s/%s", dir, filename)));
    }
}
